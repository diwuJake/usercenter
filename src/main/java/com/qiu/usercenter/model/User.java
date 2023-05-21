package com.qiu.usercenter.model;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户昵称
     */
    @TableField("user_name")
    private String user_name;

    /**
     * 登录账号
     */
    @TableField("user_account")
    private String user_account;

    /**
     * 头像
     */
    private String avatar_url;

    /**
     * 性别
     */
    private String gender;

    /**
     * 密码
     */
    @TableField("user_password")
    private String user_password;

    /**
     * 电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 更新时间（数据更新时间）
     */
    @TableField("update_time")
    private Date update_time;

    /**
     * 创建时间（数据插入时间）
     */
    @TableField("create_time")
    private Date create_time;

    /**
     * 用户状态 0-代表正常 
     */
    @TableField("user_status")
    private Integer user_status;

    /**
     * 逻辑删除
     */
    @TableLogic
    @TableField("Is_delete")
    private Integer is_delete;

    /**
     * 用户角色 0 - 代表普通用户 1- 管理员
     */

    private Integer user_role;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}