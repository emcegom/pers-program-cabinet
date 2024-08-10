package service

import (
	"context"
	"proj-gin-mall/dao"
	"proj-gin-mall/model"
	"proj-gin-mall/pkg/e"
	"proj-gin-mall/serializer"
	"proj-gin-mall/util"
)

type UserService struct {
	NickName string `json:"nick_name" form:"nick_name"`
	UserName string `json:"user_name" form:"user_name"`
	Password string `json:"password" form:"password"`
	Key      string `json:"key" form:"key"`
}

func (service UserService) Register(ctx context.Context) *serializer.Response {
	var user model.User
	code := e.Success

	if service.Key == "" || len(service.Key) != 16 {
		code = e.Error
		return &serializer.Response{
			Status: code,
			Msg:    e.GetMsg(code),
			Error:  "the length of secret key should be equal to 16!",
		}
	}
	util.Encrypt.SetKey(service.Key)

	userDao := dao.NewUserDao(ctx)
	_, exist, err := userDao.ExistOrNotByUserName(service.UserName)
	if err != nil {
		return serializer.RespByCode(e.Error)
	}
	if exist {
		return serializer.RespByCode(e.ErrorExistUser)
	}

	user = model.User{
		UserName: service.UserName,
		NickName: service.NickName,
		Status:   model.Active,
		Avatar:   "avatar.JPG",
		Money:    util.Encrypt.AesEncoding("10000"),
	}
	if err = user.SetPassword(service.Password); err != nil {
		return serializer.RespByCode(e.ErrorFailEncryption)
	}

	err = userDao.CreateUser(&user)
	if err != nil {
		code = e.Error
	}
	return serializer.RespByCode(code)
}
