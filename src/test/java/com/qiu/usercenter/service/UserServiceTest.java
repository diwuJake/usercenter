package com.qiu.usercenter.service;
import java.util.Date;

import com.qiu.usercenter.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class UserServiceTest {
    @Resource
    private  UserService userService;
    @Test
    public void testAddUser(){
        User user = new User();
        user.setUser_name("DiWu");
        user.setUser_account("12345678");
        user.setAvatar_url("http:ooo");
        user.setGender("男");
        user.setUser_password("12345678");
        user.setPhone("112412");
        user.setEmail("124241");
        user.setIs_delete(0);


        boolean result = userService.save(user);
        System.out.println(user.getId());
        Assertions.assertTrue(result);

    }

    @Test
    void userRegister() {
//            String userAccount ="diwu";
//            String userPassword = "";
//            String checkPassword ="12345678";
//            long result =userService.userRegister(userAccount,userPassword,checkPassword);
//            Assertions.assertEquals(-1,result);
//            userAccount="diw";
//            result =userService.userRegister(userAccount,userPassword,checkPassword);
//            Assertions.assertEquals(-1,result);
//            userPassword="12345";
//        result =userService.userRegister(userAccount,userPassword,checkPassword);
//        Assertions.assertEquals(-1,result);
//        userAccount="@123qiu";
//        result =userService.userRegister(userAccount,userPassword,checkPassword);
//        Assertions.assertEquals(-1,result);
//            userAccount="diwujake";
//            userPassword="12345678";
////        result =userService.userRegister(userAccount,userPassword,checkPassword);
////        Assertions.assertEquals(-1,result);
//            userAccount="diwujake";
//        result =userService.userRegister(userAccount,userPassword,checkPassword);
//        Assertions.assertTrue(result>0);

    }




}