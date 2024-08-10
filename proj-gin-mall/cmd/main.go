package main

import (
	"proj-gin-mall/conf"
	"proj-gin-mall/routes"
)

func main() {
	conf.Init()
	r := routes.NewRouter()
	_ = r.Run(conf.HttpPort)
}
