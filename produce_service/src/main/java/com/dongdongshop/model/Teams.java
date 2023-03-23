package com.dongdongshop.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 班组表
 * @TableName teams
 */
@TableName(value ="teams")
@Data
public class Teams implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 编码
     */
    private String code;

    /**
     * 班组名字
     */
    private String name;

    /**
     * 班组类型
     */
    private String type;

    /**
     * 备注
     */
    private String remarks;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}