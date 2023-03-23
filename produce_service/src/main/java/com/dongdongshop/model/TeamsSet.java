package com.dongdongshop.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName teams_set
 */
@TableName(value ="teams_set")
@Data
public class TeamsSet implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 排班计划id
     */
    private Long sid;

    /**
     * 班组id
     */
    private Long tid;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}