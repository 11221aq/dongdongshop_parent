package com.dongdongshop.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 生产报工
 * @TableName reporting
 */
@TableName(value ="reporting")
@Data
public class Reporting implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 报工类型
     */
    private Integer type;

    /**
     * 工作站名字
     */
    private String workName;

    /**
     * 工单编码
     */
    private String produceId;

    /**
     * 任务ID
     */
    private Long schedulingId;

    /**
     * 物料产品id
     */
    private String productId;

    /**
     * 物料产品名字
     */
    private String productName;

    /**
     * 单位
     */
    private String unit;

    /**
     * 规格
     */
    private String spec;

    private Long sumNum;

    /**
     * 合格品数量
     */
    private Long qualifiedNum;

    /**
     * 不良数
     */
    private Long defectsNum;

    /**
     * 报工人
     */
    private String reporter;

    /**
     * 回报时间
     */
    private Date retime;

    /**
     * 审核人
     */
    private String reviewed;

    /**
     * 备注
     */
    private String remarks;

    private Integer status;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}