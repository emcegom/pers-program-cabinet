package routes

import (
	"github.com/gin-gonic/gin"
	"net/http"
	api "proj-gin-mall/api/v1"
	"proj-gin-mall/middleware"
)

func NewRouter() *gin.Engine {
	r := gin.Default()

	r.Use(middleware.Cors())
	r.StaticFS("/static", http.Dir("./static"))
	v1 := r.Group("api/v1")
	{
		v1.GET("ping", func(ctx *gin.Context) {
			ctx.JSON(200, "success")
		})
		v1.POST("user/register", api.UserRegister)
	}
	return r
}
