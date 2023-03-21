package com.dongdongshop.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 排产
 * @TableName scheduling
 */
@TableName(value ="scheduling")
@Data
public class SchedulingVO implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 任务编号
     */
    private String asscode;

    /**
     * 任务名
     */
    private String assname;

    /**
     * 工作站编号
     */
    private String workcode;

    /**
     * 工作站名字
     */
    private String workname;

    /**
     * 排产数量
     */
    private Long schedulingnum;

    /**
     * 已生产数量
     */
    private Long productednum;

    /**
     * 开始时间
     */
    private Date starttime;

    /**
     * 生产时间
     */
    private Integer timnum;

    /**
     * 结束时间
     */
    private Date endtime;

    /**
     * 颜色
     */
    private String color;

    /**
     * 工单id
     */
    private String pid;

    private Long processid;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}