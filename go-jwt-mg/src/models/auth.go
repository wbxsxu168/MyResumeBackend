package models

import "time"

//username can be SSOID or email ..

type Useraccount struct {
	Username         *string   `json:"username" validate:"required"`
	Email            *string   `json:"email" validate:"email,required"`
	Password         *string   `json:"password" validate:"required,min=6"`
	Token            *string   `json:"token"`
	Refresh_token    *string   `json:"refresh_token"`
	CreateTime       time.Time `json:"createtime"`
	Lastmodifiedtime time.Time `json:"lastmodifiedtime"`
}
