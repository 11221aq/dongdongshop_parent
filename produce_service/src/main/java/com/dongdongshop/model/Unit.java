package com.dongdongshop.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * @TableName unit
 */
@TableName(value ="unit")
@Data
public class Unit implements Serializable {
    private String unitCode;

    private String unitName;

    private Integer unitMain;

    private Integer unitStart;

    private static final long serialVersionUID = 1L;
}