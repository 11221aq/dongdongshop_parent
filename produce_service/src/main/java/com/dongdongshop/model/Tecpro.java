package com.dongdongshop.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 工艺工序中间表
 * @TableName tecpro
 */
@TableName(value ="tecpro")
@Data
public class Tecpro implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 组成id 
     */
    private Long pid;

    /**
     * 工艺id
     */
    private Long tid;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}