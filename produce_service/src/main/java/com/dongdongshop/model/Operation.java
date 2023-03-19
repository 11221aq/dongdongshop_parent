package com.dongdongshop.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * @TableName operation
 */
@TableName(value ="operation")
@Data
public class Operation implements Serializable {
    private String oid;

    private String oname;

    private Integer status;

    private String desc;

    private static final long serialVersionUID = 1L;
}