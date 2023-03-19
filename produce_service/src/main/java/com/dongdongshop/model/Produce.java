package com.dongdongshop.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName  produce
 */
@TableName(value ="produce")
@Data
public class  Produce implements Serializable {
    @TableId
    private String pid;

    private String pname;

    private Integer psid;

    private String porder;

    private String productOrder;

    private String productName;

    private String spec;

    private String unit;

    private Long orderNum;

    private Long adjustQuantity;

    private Long quantitProduced;

    private Long batchNum;

    private String customerCode;

    private String customerName;

    private Date needTime;

    private Integer status;

    private String parentid;

    private static final long serialVersionUID = 1L;
}