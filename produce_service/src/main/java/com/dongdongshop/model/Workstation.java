package com.dongdongshop.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName workstation
 */
@TableName(value ="workstation")
@Data
public class Workstation implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 工作编码
     */
    private String workcode;

    /**
     * 工作站名字
     */
    private String workname;

    /**
     * 工作站地点
     */
    private String workplace;

    /**
     * 车间id
     */
    private Long farmid;

    /**
     * 工艺ID
     */
    private String oid;

    /**
     * 是否启用    0启用  1 不
     */
    private Integer status;

    /**
     * 备注
     */
    private String remarks;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}