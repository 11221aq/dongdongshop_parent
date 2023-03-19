package com.dongdongshop.model;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 编码生成规则组成表
 * @TableName sys_auto_code_part
 */
@TableName(value ="sys_auto_code_part")
@Data
public class CodePart implements Serializable {
    /**
     * 分段ID
     */
    @TableId(type = IdType.AUTO)
    private Long partId;

    /**
     * 规则ID
     */
    private Long ruleId;

    /**
     * 分段序号
     */
    private Integer partIndex;

    /**
     * 分段类型，INPUTCHAR：输入字符，NOWDATE：当前日期时间，FIXCHAR：固定字符，SERIALNO：流水号
     */
    private String partType;

    /**
     * 分段编号
     */
    private String partCode;

    /**
     * 分段名称
     */
    private String partName;

    /**
     * 分段长度
     */
    private Integer partLength;

    /**
     * 
     */
    private String dateFormat;

    /**
     * 输入字符
     */
    private String inputCharacter;

    /**
     * 固定字符
     */
    private String fixCharacter;

    /**
     * 流水号起始值
     */
    private Integer seriaStartNo;

    /**
     * 流水号步长
     */
    private Integer seriaStep;

    /**
     * 流水号当前值
     */
    private Integer seriaNowNo;

    /**
     * 流水号是否循环
     */
    private String cycleFlag;

    /**
     * 循环方式，YEAR：按年，MONTH：按月，DAY：按天，HOUR：按小时，MINITE：按分钟，OTHER：按传入字符变
     */
    private String cycleMethod;

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