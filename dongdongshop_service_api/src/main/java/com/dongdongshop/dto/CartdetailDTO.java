package com.dongdongshop.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class CartdetailDTO implements Serializable {
    private Long itemId;
    private Long goodsId;
    private BigDecimal price;
    private Integer num;
    private BigDecimal totalFee;
    private String picPath;
    private String title;
}
