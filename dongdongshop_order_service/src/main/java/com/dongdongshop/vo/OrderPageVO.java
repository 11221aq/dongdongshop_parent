package com.dongdongshop.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
@Data
public class OrderPageVO {

    private Long id;

    private Long itemId;

    private Long goodsId;

    private Long orderId;

    private String title;

    private BigDecimal price;

    private Integer num;

    private BigDecimal totalFee;

    private String picPath;

    private String sellerId;

    private String orderNum;

    private String tradeNo;

    private String status;
}
