package com.dongdongshop.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
@Data
public class CartdetailVO implements Serializable {

    private static final long serialVersionUID = 2209429219061291428L;
    private Long itemId;
    private Long goodsId;
    private BigDecimal price;
    private Integer num;
    private BigDecimal totalFee;
    private String picPath;
    private String title;
}
