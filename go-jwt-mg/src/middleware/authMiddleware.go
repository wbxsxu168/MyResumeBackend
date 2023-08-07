package middleware

// reference : jwt logic mainly referred from  https://github.com/akmamun/go-jwt  etc.
import (
	"fmt"
	helper "go-jwt-mg/src/helpers"
	"net/http"
	"strings"

	"github.com/gin-gonic/gin"
)

// authentication and authorization
func Authentication() gin.HandlerFunc {
	return func(c *gin.Context) {
		clientToken0 := c.Request.Header.Get("Authorization") // changed from token to Authorization
		if clientToken0 == "" {
			c.JSON(http.StatusInternalServerError, gin.H{"error": fmt.Sprintf("Missing JWT token in the HTTP request header!")})
			c.Abort()
			return
		}
		clientToken := strings.Split(clientToken0, "Bearer ")[1]

		claims, err := helper.ValidateToken(clientToken)
		if err != "" {
			c.JSON(http.StatusInternalServerError, gin.H{"error": err})
			c.Abort()
			return
		}

		c.Set("email", claims.Email)
		c.Next()

	}
}
