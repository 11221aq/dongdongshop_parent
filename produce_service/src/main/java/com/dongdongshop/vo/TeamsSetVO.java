package com.dongdongshop.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 
 * @TableName teams_set
 */
@TableName(value ="teams_set")
@Data
public class TeamsSetVO implements Serializable {
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