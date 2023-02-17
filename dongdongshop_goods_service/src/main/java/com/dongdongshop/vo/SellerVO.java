package com.dongdongshop.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SellerVO {
    private String sellerId;//用户id
    private Integer statusId;

    private String name;//公司名字

    private String nickName;//店铺名

    private String password;//密码

    private String email;//email

    private String mobile;//公司手机

    private String telephone;//公司电话

    private String status;//审核状态

    private String addressDetail;//详细地址

    private String linkmanName;//联系人姓名

    private String linkmanQq;//联系人QQ

    private String linkmanMobile;//联系人电话

    private String linkmanEmail;//联系人email

    private String licenseNumber;//营业执照号

    private String taxNumber;//税务登记号

    private String orgNumber;//组织机构代码

    private Long address;//公司地址

    private String logoPic;//公司log图

    private String brief;//简介

    private Date createTime;//创建日期

    private String legalPerson;//法定代表人

    private String legalPersonCardId;//法定身份证号

    private String bankUser;//开户行账户名称

    private String bankName;//开户行

}
