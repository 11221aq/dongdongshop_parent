package com.dongdongshop.dto;

import lombok.Data;

import java.util.Date;
@Data
public class LoginUserDTO {
    private Long id;

    private String username;

    private String password;

    private String salt;

    private String phone;

    private String email;

    private Date created;

    private Date updated;

    private String sourceType;

    private String nickName;

    private String name;

    private String status;

    private String headPic;

    private String qq;

    private Long accountBalance;

    private String isMobileCheck;

    private String isEmailCheck;

    private String sex;

    private Integer userLevel;

    private Integer points;

    private Integer experienceValue;

    private Date birthday;

    private Date lastLoginTime;
}
