package com.dongdongshop.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 
 * @TableName classes
 */
@TableName(value ="classes")
@Data
public class ClassesVO implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 班次名称
     */
    private String name;

    /**
     * 班次名称
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 父节点
     */
    private Long sid;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}