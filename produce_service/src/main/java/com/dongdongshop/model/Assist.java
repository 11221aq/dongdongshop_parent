package com.dongdongshop.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * @TableName assist
 */
@TableName(value ="assist")
@Data
public class Assist implements Serializable {
    private Integer aid;

    private Integer asort;

    private String aexplain;

    private String aequipment;

    private String amaterial;

    private String adesc;

    private String pid;

    private static final long serialVersionUID = 1L;
}