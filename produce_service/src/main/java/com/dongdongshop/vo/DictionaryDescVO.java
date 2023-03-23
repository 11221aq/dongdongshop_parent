package com.dongdongshop.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 字典明细表
 * @TableName dictionary_desc
 */
@TableName(value ="dictionary_desc")
@Data
public class DictionaryDescVO implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 父类
     */
    private String pid;

    /**
     * 数据标签
     */
    private String label;

    /**
     * 字典键值
     */
    private String keyvel;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 创建时间
     */
    private Date creatTime;

    /**
     * 样式属性
     */
    private String style;

    /**
     * 回显样式
     */
    private String echo;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}