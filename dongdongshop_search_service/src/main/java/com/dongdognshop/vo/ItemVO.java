package com.dongdognshop.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
@Data
public class ItemVO {
    private Long id;

    private String title;

    private String sellPoint;

    private BigDecimal price;

    private Integer stockCount;

    private Integer num;

    private String barcode;

    private String image;

    private Long categoryid;

    private String status;

    private Date createTime;

    private Date updateTime;

    private String itemSn;

    private BigDecimal costPirce;

    private BigDecimal marketPrice;

    private String isDefault;

    private Long goodsId;

    private String sellerId;

    private String cartThumbnail;

    private String category;

    private String brand;

    private String spec;

    private String seller;
}
