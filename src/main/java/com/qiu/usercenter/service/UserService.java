package com.qiu.usercenter.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qiu.usercenter.model.User;

import javax.servlet.http.HttpServletRequest;


/**
* @author 邱文杰·
* @description 针对表【user(用户)】的数据库操作Service
* @createDate 2023-05-02 16:53:07
*/
public interface UserService extends IService<User> {

    /**
     * 用户的注册
     * @param userAccount 用户账户
     * @param userPassword 用户密码
     * @param checkPassword 校验密码
     * @return 新用户的id
     */
    long userRegister (String userAccount,String userPassword,String checkPassword);

    /**
     * 用户的登录
     * @param userAccount 用户账户
     * @param userPassword 用户密码
     * @param request
     * @return  脱敏后的用户信息
     */
    User userLogin(String userAccount, String userPassword, HttpServletRequest request);

    /**
     * 用户的脱敏
     * @param originUser
     * @return
     */
    User getSafetyUser(User originUser);
}
