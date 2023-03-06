package com.dongdongshop.vo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class SeckillGoodsVO implements Serializable {
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

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    private Integer num;

    private Integer stockCount;

    private String introduction;
}