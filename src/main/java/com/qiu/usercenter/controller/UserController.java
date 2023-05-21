package com.qiu.usercenter.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qiu.usercenter.constant.UserConstant;
import com.qiu.usercenter.model.User;
import com.qiu.usercenter.model.request.UserLoginRequest;
import com.qiu.usercenter.model.request.UserRegisterRequest;
import com.qiu.usercenter.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户的接口
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;
    @PostMapping("/register")
    public Long userRegister(@RequestBody UserRegisterRequest userRegisterRequest){
        if(userRegisterRequest==null){
           return null;
        }
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        if (StringUtils.isAnyBlank(userAccount,userPassword,checkPassword)){
            return null;
        }
        return   userService.userRegister(userAccount, userPassword, checkPassword);

    }
    @PostMapping("/login")
    public User userLogin(@RequestBody UserLoginRequest userLoginRequest , HttpServletRequest request){
        if(userLoginRequest==null){
            return null;
        }
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();
        if (StringUtils.isAnyBlank(userAccount,userPassword)){
            return null;
        }
        return   userService.userLogin(userAccount,userPassword,request);
    }

    /**
     * 根据用户名查询用户
     * @return
     */
    @GetMapping("/search")
   public List<User> searchUsers(String username,HttpServletRequest request){
        if(!isAdmin(request)){
            return new ArrayList<>();
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if(StringUtils.isNotBlank(username)){
            queryWrapper.like("user_name",username);
        }
        List<User> list = userService.list(queryWrapper);
        return list.stream().map(user -> {
            return userService.getSafetyUser(user);
        }).collect(Collectors.toList());
    }

    @GetMapping("/current")
    public  User getCurrentUser(HttpServletRequest request){
        Object attribute = request.getSession().getAttribute(UserConstant.USER_LOGIN_STATE);
        User currentUser =(User) attribute;
       if(currentUser == null){
           return  null;
       }

        return userService.getSafetyUser(currentUser);
    }

   @PostMapping("/delete")
   public boolean deleteUser(long id,HttpServletRequest request){
      if(!isAdmin(request)){
         return false;
      }
        if(id<=0){
            return false;
        }
        return userService.removeById(id);
   }


  public boolean isAdmin(HttpServletRequest request){

      //管理员权限
      Object attribute = request.getSession().getAttribute(UserConstant.USER_LOGIN_STATE);
      User user =(User) attribute;
      if (user==null||user.getUser_role()!=UserConstant.ADMIT_RolE){
          return false;
      }
      return true;
  }
}
