package com.dongdongshop.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 工序表
 * @TableName operation
 */
@TableName(value ="operation")
@Data
public class Operation implements Serializable {
    /**
     * 主键
     */
    @TableId
    private String oid;

    /**
     * 名字
     */
    private String oname;

    /**
     * 是否启用 0 启用 1 禁用
     */
    private Integer status;

    /**
     * 备注
     */
    private String descc;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}