package com.qiu.usercenter;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qiu.usercenter.mapper.UserMapper;
import com.qiu.usercenter.model.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
@Slf4j
class UsercenterApplicationTests {

    @Test
    void contextLoads() {

    }

//    @Test
//    public User  selectTest(){
//
//       @Resource
//       public UserMapper userMapper;
//        String userAccount = "diwujake";
//        String userPassword="12345678";
//        //查询用户是否在数据库中
//        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
//        userQueryWrapper.eq("user_account",userAccount);
//        userQueryWrapper.eq("user_password",userPassword);
//        User user =
//        if (user==null){
//            log.info("user login failed");
//            return null;
//        }
//    }
}
