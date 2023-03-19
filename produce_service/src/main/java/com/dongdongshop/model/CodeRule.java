package com.dongdongshop.model;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 编码生成规则表
 * @TableName sys_auto_code_rule
 */
@TableName(value ="sys_auto_code_rule")
@Data
public class CodeRule implements Serializable {
    /**
     * 规则ID
     */
    @TableId(type = IdType.AUTO)
    private Long ruleId;

    /**
     * 规则编码
     */
    private String ruleCode;

    /**
     * 规则名称
     */
    private String ruleName;

    /**
     * 描述
     */
    private String ruleDesc;

    /**
     * 最大长度
     */
    private Integer maxLength;

    /**
     * 是否补齐
     */
    private String isPadded;

    /**
     * 补齐字符
     */
    private String paddedChar;

    /**
     * 补齐方式
     */
    private String paddedMethod;

    /**
     * 是否启用
     */
    private String enableFlag;

    /**
     * 备注
     */
    private String remark;

    /**
     * 预留字段1
     */
    @TableField(select = false)//不参与查询
    private String attr1;

    /**
     * 预留字段2
     */
    @TableField(select = false)//不参与查询
    private String attr2;

    /**
     * 预留字段3
     */
    @TableField(select = false)//不参与查询
    private Integer attr3;

    /**
     * 预留字段4
     */
    @TableField(select = false)//不参与查询
    private Integer attr4;

    /**
     * 创建者
     */
    @TableField(select = false)//不参与查询
    private String createBy;

    private Date createTime;

    /**
     * 更新者
     */
    @TableField(select = false)//不参与查询
    private String updateBy;

    private Date updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}