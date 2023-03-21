package com.dongdongshop.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 工单工序表
 * @TableName wootable
 */
@TableName(value ="wootable")
@Data
public class WootableVO implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 工单ID
     */
    private String pid;

    /**
     * 工艺表
     */
    private String oid;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}