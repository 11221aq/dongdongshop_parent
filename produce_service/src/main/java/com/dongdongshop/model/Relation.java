package com.dongdongshop.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 工序关系
 * @TableName relation
 */
@TableName(value ="relation")
@Data
public class Relation implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer rid;

    /**
     * rname
     */
    private String rname;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}