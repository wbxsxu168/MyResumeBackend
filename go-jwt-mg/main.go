package main

import (
	middleware "go-jwt-mg/src/middleware"
	routes "go-jwt-mg/src/routes"
	"html/template"
	"os"
	"strings"
	"time"

	"github.com/gin-contrib/cors"
	"github.com/gin-gonic/gin"
)

func main() {
	port := os.Getenv("PORT")

	if port == "" {
		port = "8082"
	}
	gin.SetMode(gin.ReleaseMode)

	router := gin.New()
	//CORS support added as :  "X-Requested-With", "Content-Type", "Accept", "Authorization", "Access-Control-Allow-Origin", "Access-Control-Request-Method", "Access-Control-Request-Headers"
	router.Use(cors.New(cors.Config{
		AllowOrigins:     []string{"http://localhost:3000", "https://wbxsxu168.github.io"},
		AllowMethods:     []string{"DELETE", "GET", "OPTIONS", "PATCH", "POST", "PUT"},
		AllowHeaders:     []string{"accept", "authorization", "content-type", "user-agent", "x-csrftoken", "x-requested-with"},
		ExposeHeaders:    []string{"Content-Length", "Access-Control-Allow-Origin"},
		AllowCredentials: true,
		/*AllowOriginFunc: func(origin string) bool {
			return origin == "http://localhost:3000"
		},*/
		MaxAge: 60 * time.Minute,
	}))

	router.SetFuncMap(template.FuncMap{
		"upper": strings.ToUpper,
	})
	//	router.Static("/assets", "./assets")
	router.LoadHTMLGlob("src/templates/*.html")
	router.Use(gin.Logger())
	routes.UserRoutes(router)
	router.Use(middleware.Authentication())
	routes.HomeRoutes(router)

	router.Run(":" + port)
}
