package com.dongdongshop.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName teams_user
 */
@TableName(value ="teams_user")
@Data
public class TeamsUser implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 班组id
     */
    private Long tid;

    /**
     * 用户id
     */
    private Long uid;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}