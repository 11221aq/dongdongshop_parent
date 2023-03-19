package com.dongdongshop.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * @TableName customer
 */
@TableName(value ="customer")
@Data
public class Customer implements Serializable {
    private String id;

    private String name;

    private String shortName;

    private String type;

    private String phone;

    private String contacts;

    private String contactPhone;

    private Integer status;

    private static final long serialVersionUID = 1L;
}