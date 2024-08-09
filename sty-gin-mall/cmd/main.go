package main

import (
	"sty-gin-mall/conf"
	"sty-gin-mall/routes"
)

func main() {
	conf.Init()
	r := routes.NewRouter()
	_ = r.Run(conf.HttpPort)
}
