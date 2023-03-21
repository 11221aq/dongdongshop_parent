package com.dongdongshop.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 车间
 * @TableName farm
 */
@TableName(value ="farm")
@Data
public class Farm implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 车间编码
     */
    private String fcode;

    /**
     * 车间名字
     */
    private String fname;

    /**
     * 面积
     */
    private Integer area;

    /**
     * 负责人
     */
    private String person;

    /**
     * 备注
     */
    private String remarks;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}