package serializer

import "proj-gin-mall/pkg/e"

type Response struct {
	Status int         `json:"status"`
	Data   interface{} `json:"data"`
	Msg    string      `json:"msg"`
	Error  string      `json:"error"`
}

func RespByCode(code int) (resp *Response) {
	return &Response{Status: code, Msg: e.GetMsg(code)}
}
