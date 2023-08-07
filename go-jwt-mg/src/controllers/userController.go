package controllers

import (
	"context"
	"fmt"
	"go-jwt-mg/src/database"
	helper "go-jwt-mg/src/helpers"
	"go-jwt-mg/src/models"
	"log"
	"net/http"
	"time"

	"github.com/gin-gonic/gin"
	"github.com/go-playground/validator/v10"
	"go.mongodb.org/mongo-driver/bson"
	"go.mongodb.org/mongo-driver/mongo"
	"golang.org/x/crypto/bcrypt"
)

var userCollection *mongo.Collection = database.OpenCollection(database.Client, "Useraccount")
var validate = validator.New()

// HashPassword is used to encrypt the password
func HashPassword(password string) string {
	bytes, err := bcrypt.GenerateFromPassword([]byte(password), 14)
	if err != nil {
		log.Panic(err)
	}
	return string(bytes)
}

// VerifyPassword checks
func VerifyPassword(userPassword string, providedPassword string) (bool, string) {
	err := bcrypt.CompareHashAndPassword([]byte(providedPassword), []byte(userPassword))
	check := true
	msg := ""

	if err != nil {
		msg = fmt.Sprintf("username/email or passowrd doesnot match records!")
		check = false
	}
	return check, msg
}

// sign up a new user
func SignUp() gin.HandlerFunc {
	return func(c *gin.Context) {
		var ctx, cancel = context.WithTimeout(context.Background(), 100*time.Second)
		var user models.Useraccount

		if err := c.BindJSON(&user); err != nil {
			c.JSON(http.StatusBadRequest, gin.H{"error": err.Error()})
			return
		}

		validationErr := validate.Struct(user)
		if validationErr != nil {
			c.JSON(http.StatusBadRequest, gin.H{"success": false, "data": nil, "message": validationErr.Error()})
			return
		}

		count, err := userCollection.CountDocuments(ctx, bson.M{"email": user.Email})
		defer cancel()
		if err != nil {
			log.Panic(err)
			c.JSON(http.StatusInternalServerError,
				gin.H{"success": false, "data": nil, "message": "error occured while looking up the email"})

			return
		}

		password := HashPassword(*user.Password)
		user.Password = &password

		if count > 0 {
			c.JSON(http.StatusConflict,
				gin.H{"success": false, "data": nil, "message": "this email address already exists!"})

			return
		}

		user.Username = user.Email
		user.CreateTime, _ = time.Parse(time.RFC3339, time.Now().Format(time.RFC3339))
		user.Lastmodifiedtime, _ = time.Parse(time.RFC3339, time.Now().Format(time.RFC3339))

		token, refreshToken, _ := helper.GenerateAllTokens(*user.Email)
		user.Token = &token
		user.Refresh_token = &refreshToken

		result, insertErr := userCollection.InsertOne(ctx, user)
		if insertErr != nil {
			msg := fmt.Sprintf("User was not created due to exception in insert DB as: %s", insertErr)
			c.JSON(http.StatusInternalServerError,
				gin.H{"success": false, "data": nil, "message": msg})
			return
		}

		defer cancel()
		c.JSON(http.StatusOK, gin.H{"success": true, "data": result.InsertedID, "message": "user sign up succeed!"})
	}
}

// Login user
func Login() gin.HandlerFunc {
	return func(c *gin.Context) {
		var ctx, cancel = context.WithTimeout(context.Background(), 100*time.Second)
		var user models.Useraccount
		var foundUser models.Useraccount

		if err := c.BindJSON(&user); err != nil {
			c.JSON(http.StatusBadRequest, gin.H{"error": err.Error()})
			return
		}

		user.Email = user.Username

		err := userCollection.FindOne(ctx, bson.M{"email": user.Email}).Decode(&foundUser)
		defer cancel()
		if err != nil {
			c.JSON(http.StatusOK, gin.H{"error": "login username/email or passowrd is incorrect!"})
			return
		}

		passwordIsValid, msg := VerifyPassword(*user.Password, *foundUser.Password)
		defer cancel()
		if passwordIsValid != true {
			c.JSON(http.StatusInternalServerError, gin.H{"error": msg})
			return
		}

		if foundUser.Email == nil {
			//from User not found! to Invalid input or system error! for security enhancement
			c.JSON(http.StatusOK, gin.H{"error": "Invalid input or system error!"})
			return
		}
		token, refreshToken, _ := helper.GenerateAllTokens(*foundUser.Email)

		helper.UpdateAllTokens(token, refreshToken, *foundUser.Email)
		err = userCollection.FindOne(ctx, bson.M{"email": foundUser.Email}).Decode(&foundUser)

		if err != nil {
			c.JSON(http.StatusInternalServerError, gin.H{"error": err.Error()})
			return
		}

		c.JSON(http.StatusOK,
			gin.H{
				"access":  foundUser.Token,
				"refresh": foundUser.Refresh_token},
		)
	}
}
