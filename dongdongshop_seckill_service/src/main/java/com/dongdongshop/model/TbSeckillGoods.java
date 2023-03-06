package com.dongdongshop.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
@Data
public class TbSeckillGoods {
    private Long id;

    private Long goodsId;

    private Long itemId;

    private String title;

    private String smallPic;

    private BigDecimal price;

    private BigDecimal costPrice;

    private String sellerId;

    private Date createTime;

    private Date checkTime;

    private String status;

    private Date startTime;

    private Date endTime;

    private Integer num;

    private Integer stockCount;

    private String introduction;
}