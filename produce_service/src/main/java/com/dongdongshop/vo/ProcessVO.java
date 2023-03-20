package com.dongdongshop.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 工艺组成
 * @TableName process
 */
@TableName(value ="process")
@Data
public class ProcessVO implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long pid;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 工艺id
     */
    private String operation;

    /**
     * 与下一道工序关系
     */
    private Integer relation;

    /**
     * 颜色
     */
    private String color;

    /**
     * 是否是关键    0关键 1不
     */
    private Integer hinge;

    /**
     * 准备时间
     */
    private Integer prepare;

    /**
     * 等待时间
     */
    private Integer wait;

    /**
     * 备注
     */
    private String remarks;

    private Long tid;
}