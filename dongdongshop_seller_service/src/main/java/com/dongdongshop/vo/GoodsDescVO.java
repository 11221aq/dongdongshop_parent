package com.dongdongshop.vo;

import lombok.Data;

@Data
public class GoodsDescVO {
    private Long goodsId;

    private String introduction;

    private String specificationItems;

    private String customAttributeItems;

    private String itemImages;

    private String packageList;

    private String saleService;
}
