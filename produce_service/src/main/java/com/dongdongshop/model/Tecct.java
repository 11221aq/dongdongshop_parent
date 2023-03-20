package com.dongdongshop.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 工艺物料产品中间表
 * @TableName tecct
 */
@TableName(value ="tecct")
@Data
public class Tecct implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 工艺id
     */
    private Long pid;

    /**
     * 物料产品id
     */
    private String productid;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}