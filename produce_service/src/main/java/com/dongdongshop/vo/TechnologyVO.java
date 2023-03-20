package com.dongdongshop.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 工艺表
 * @TableName technology
 */
@TableName(value ="technology")
@Data
public class TechnologyVO implements Serializable {
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
    private String explain;

    /**
     * 备注
     */
    private String remarks;
}