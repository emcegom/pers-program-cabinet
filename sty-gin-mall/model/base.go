package model

type BasePage struct {
	PageNum  int `form:"PageNum"`
	PageSize int `form:"PageSize"`
}
