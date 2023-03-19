package com.dongdongshop.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;


@Data
public class ProductVO implements Serializable {
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
}