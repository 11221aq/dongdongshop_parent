package com.dongdongshop.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 辅助
 * @TableName assist
 */
@TableName(value ="assist")
@Data
public class Assist implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer aid;

    /**
     * 排序
     */
    private Integer asort;

    /**
     * 材料url
     */
    private String aurl;

    /**
     * 步骤说明
     */
    private String aexplain;

    /**
     * 辅助设备
     */
    private String aequipment;

    /**
     * 辅助材料
     */
    private String amaterial;

    /**
     * 备注
     */
    private String adesc;

    /**
     * 父节点
     */
    private String pid;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}