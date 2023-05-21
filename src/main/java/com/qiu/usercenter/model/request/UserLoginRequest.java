package com.qiu.usercenter.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户的登录请求
 */
@Data
public class UserLoginRequest implements Serializable {


    private static final long serialVersionUID = -1911683517343913767L;
    private String  userAccount;
    private String userPassword;
}
