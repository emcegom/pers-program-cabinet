package conf

import (
	"gopkg.in/ini.v1"
	"proj-gin-mall/dao"
	"strings"
)

var (
	AppMode     string
	HttpPort    string
	DB          string
	DbHost      string
	DbPort      string
	DbUser      string
	DbPassword  string
	DbName      string
	RedisDb     string
	RedisAddr   string
	RedisPw     string
	RedisDbName string
	ValidEmail  string
	SmtpHost    string
	SmtpEmail   string
	SmtpPass    string
	Host        string
	ProductPath string
	AvatarPath  string
)

func Init() {
	file, err := ini.Load("./conf/config.ini")
	if err != nil {
		panic(err)
	}
	LoadServer(file)
	LoadMySQL(file)
	LoadRedis(file)
	LoadEmail(file)
	LoadPhotoPath(file)

	pathRead := strings.Join([]string{DbUser, ":", DbPassword, "@tcp(", DbHost, ":", DbPort, ")/", DbName, "?charset=utf8mb4&parseTime=true"}, "")
	pathWrite := strings.Join([]string{DbUser, ":", DbPassword, "@tcp(", DbHost, ":", DbPort, ")/", DbName, "?charset=utf8mb4&parseTime=true"}, "")

	dao.Database(pathRead, pathWrite)
}

func LoadPhotoPath(file *ini.File) {
	Host = file.Section("path").Key("Host").String()
	ProductPath = file.Section("path").Key("ProductPath").String()
	AvatarPath = file.Section("path").Key("AvatarPath").String()
}

func LoadEmail(file *ini.File) {
	ValidEmail = file.Section("mail").Key("ValidEmail").String()
	SmtpHost = file.Section("mail").Key("SmtpHost").String()
	SmtpEmail = file.Section("mail").Key("SmtpEmail").String()
	SmtpPass = file.Section("mail").Key("SmtpPass").String()
}

func LoadRedis(file *ini.File) {
	RedisDb = file.Section("redis").Key("RedisDb").String()
	RedisAddr = file.Section("redis").Key("RedisAddr").String()
	RedisPw = file.Section("redis").Key("RedisPw").String()
	RedisDbName = file.Section("redis").Key("RedisDbName").String()
}

func LoadMySQL(file *ini.File) {
	DB = file.Section("mysql").Key("DB").String()
	DbHost = file.Section("mysql").Key("DbHost").String()
	DbPort = file.Section("mysql").Key("DbPort").String()
	DbUser = file.Section("mysql").Key("DbUser").String()
	DbPassword = file.Section("mysql").Key("DbPassword").String()
	DbName = file.Section("mysql").Key("DbName").String()
}

func LoadServer(file *ini.File) {
	AppMode = file.Section("service").Key("AppMode").String()
	HttpPort = file.Section("service").Key("HttpPort").String()
}
