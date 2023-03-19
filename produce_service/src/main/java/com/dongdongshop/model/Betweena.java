package com.dongdongshop.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * @TableName betweena
 */
@TableName(value ="betweena")
@Data
public class Betweena implements Serializable {
    private Integer bid;

    private String pid;

    private String cid;

    private static final long serialVersionUID = 1L;
}