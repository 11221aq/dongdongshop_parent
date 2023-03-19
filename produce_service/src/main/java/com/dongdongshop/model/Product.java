package com.dongdongshop.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * @TableName product
 */
@TableName(value ="product")
@Data
public class Product implements Serializable {
    @TableId
    private String productId;

    private String productName;

    private String productSpec;

    private String productUnit;

    private Integer productMapr;

    private String productTimate;

    private Double productScale;

    private String productPid;

    private Integer productBomNeed;

    private String productType;

    private static final long serialVersionUID = 1L;
}