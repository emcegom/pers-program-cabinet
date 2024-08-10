package e

var MsgFlags = map[int]string{
	Success:       "ok",
	Error:         "fail",
	InvalidParams: "wrong params",

	ErrorExistUser:      "user exists",
	ErrorFailEncryption: "fail encryption",
}

func GetMsg(code int) string {
	msg, ok := MsgFlags[code]
	if !ok {
		return "Undefined error message"
	}
	return msg
}
