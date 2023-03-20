package com.dongdongshop.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 工艺表
 * @TableName technology
 */
@TableName(value ="technology")
@Data
public class Technology implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long tid;

    /**
     * 编码
     */
    private String tcode;

    /**
     * 名称
     */
    private String tname;

    /**
     * 启用    0启用    1不启用
     */
    private Integer status;

    /**
     * 说明
     */
    @TableField(value = "`explain`")
    private String explain;

    /**
     * 备注
     */
    private String remarks;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}