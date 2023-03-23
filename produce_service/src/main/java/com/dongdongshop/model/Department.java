package com.dongdongshop.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 部门表
 * @TableName department
 */
@TableName(value ="department")
@Data
public class Department implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 部门名字
     */
    private String name;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 负责人
     */
    private String person;

    /**
     * 电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 父节点
     */
    private Integer pid;

    /**
     * 状态  0 ,启用  1不
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date creatTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}