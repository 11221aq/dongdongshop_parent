package com.dongdongshop.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 岗位表
 * @TableName post
 */
@TableName(value ="post")
@Data
public class PostVO implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 岗位编码
     */
    private String pcode;

    /**
     * 岗位名字
     */
    private String pname;

    /**
     * 排序
     */
    private Integer psort;

    /**
     * 状态 0启用 1不启用
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date creatTime;

    /**
     * 备注
     */
    private String remarks;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}