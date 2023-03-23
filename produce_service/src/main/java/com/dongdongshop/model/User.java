package com.dongdongshop.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 用户表
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 名称 
     */
    private String uname;

    /**
     * 密码
     */
    private String password;

    /**
     * 昵称
     */
    private String nick;

    /**
     * 部门
     */
    private String department;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 状态  0  可用   1 不可用
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date creatTime;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 性别
     */
    private String sex;

    /**
     * 岗位
     */
    private String post;

    /**
     * 角色
     */
    private String role;

    /**
     * 备注
     */
    private String remarks;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}