package com.dongdongshop.dto;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
@Builder
public class SellerDTO {
    private String sellerId;

    private String name;

    private String nickName;

    private String password;

    private String email;

    private String mobile;

    private String telephone;

    private String status;

    private String addressDetail;

    private String linkmanName;

    private String linkmanQq;

    private String linkmanMobile;

    private String linkmanEmail;

    private String licenseNumber;

    private String taxNumber;

    private String orgNumber;

    private Long address;

    private String logoPic;

    private String brief;

    private Date createTime;

    private String legalPerson;

    private String legalPersonCardId;

    private String bankUser;

    private String bankName;

    private Integer bankAccount;
}
