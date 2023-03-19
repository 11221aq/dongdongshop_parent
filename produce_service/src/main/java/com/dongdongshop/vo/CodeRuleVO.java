package com.dongdongshop.vo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Data
public class CodeRuleVO implements Serializable {
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


    private String attr1;

    /**
     * 预留字段2
     */
    private String attr2;

    /**
     * 预留字段3
     */
    private Integer attr3;

    /**
     * 预留字段4
     */
    private Integer attr4;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新者
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private Date updateTime;

}