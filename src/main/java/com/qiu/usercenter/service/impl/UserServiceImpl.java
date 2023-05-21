package com.qiu.usercenter.service.impl;
import java.util.Date;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qiu.usercenter.mapper.UserMapper;
import com.qiu.usercenter.model.User;
import com.qiu.usercenter.service.UserService;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.qiu.usercenter.constant.UserConstant.USER_LOGIN_STATE;

/**
* @author 第五
* @description 针对表【user(用户)】的数据库操作Service实现
* @createDate 2023-05-02 16:53:07
*/

@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {
    @Resource
        private  UserMapper userMapper;
    /**
     * 盐值
     */
        private static  final String SALT = "diwu";


    @Override
    public long userRegister(String userAccount, String userPassword, String checkPassword) {
        //1.校验
        //1.1 判断是否输入的账户是否为空
        if(StringUtils.isAnyBlank(userAccount,userPassword,checkPassword)){
            return -1;
        }
        //1.2 账户的长度不小于4
        if(userAccount.length()<4){
            return  -1;
        }
        //1.3 密码的长度不小于8位
        if(userPassword.length()<8|| checkPassword.length()<8){
            return -1;
        }

        //1.4账户不能包含特殊的字符
        String validPattern = "[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Matcher matcher = Pattern.compile(validPattern).matcher(userAccount);
        if (matcher.find()) {
            return -1;
        }
        //1.5 密码和校验密码得相同
        if (!userPassword.equals(checkPassword)) {
            return -1;
        }

        //1.6 账户不能重复
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_account", userAccount);
        long count = userMapper.selectCount(queryWrapper);
        if (count > 0) {
            return -1;
        }

        //2 加密
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
        //3.插入数据
        User user = new User();
        user.setUser_account(userAccount);
        user.setUser_password(userPassword);
        boolean saveResult = this.save(user);
        if (!saveResult) {
            return -1;
        }
        return user.getId();
    }

    @Override
    public User userLogin(String userAccount, String userPassword, HttpServletRequest request) {
        //1.校验
        //1.1 判断是否输入的账户是否为空
        if(StringUtils.isAnyBlank(userAccount,userPassword)){
            return null;
        }
        //1.2 账户的长度不小于4
        if(userAccount.length()<4){
            return  null;
        }
        //1.3 密码的长度不小于8位
        if(userPassword.length()<8){
            return null;
        }
        //1.4账户不能包含特殊的字符
        String validPattern = "[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Matcher matcher = Pattern.compile(validPattern).matcher(userAccount);
        if (matcher.find()) {
            return null;
        }
        //2 加密
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());

        //查询用户是否存在
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("user_account",userAccount);
        userQueryWrapper.eq("user_password",userPassword);
        User user = userMapper.selectOne(userQueryWrapper);
        if (user==null){
            log.info("user login failed");
            return null;
        }
        //用户脱敏
        User safetyUser = getSafetyUser(user);
        //记录用户的登录态
        request.getSession().setAttribute(USER_LOGIN_STATE,user);

        return user;
    }
    @Override
    public  User getSafetyUser(User originUser){
        //用户脱敏
        if (originUser == null){
            return  null;
        }
        User safeUser= new User();
        safeUser.setId(originUser.getId());
        safeUser.setUser_name(originUser.getUser_name());
        safeUser.setUser_account(originUser.getUser_account());
        safeUser.setAvatar_url(originUser.getAvatar_url());
        safeUser.setGender(originUser.getGender());
        safeUser.setPhone(originUser.getPhone());
        safeUser.setEmail(originUser.getEmail());
        safeUser.setCreate_time(originUser.getCreate_time());
        safeUser.setUser_status(originUser.getUser_status());
        safeUser.setUser_role(originUser.getUser_role());
        return  safeUser;
    }
}




