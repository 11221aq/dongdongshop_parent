package com.dongdongshop.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
public class CustomerVO implements Serializable {
    private String id;

    private String name;

    private String shortName;

    private String type;

    private String phone;

    private String contacts;

    private String contactPhone;

    private Integer status;
}