package com.dongdongshop.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 字典
 * @TableName dictionary
 */
@TableName(value ="dictionary")
@Data
public class DictionaryVO implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 字典名字
     */
    private String name;

    /**
     * 字典类型
     */
    private String code;

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

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}