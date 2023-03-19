package com.dongdongshop.vo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class CodePartVO implements Serializable {
    /**
     * 分段ID
     */
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