package com.dongdongshop.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class GoodsVO {
    private Long id;

    private String sellerId;

    private String goodsName;

    private Long defaultItemId;

    private String auditStatus;

    private String isMarketable;

    private Long brandId;

    private String caption;

    private Long category1Id;

    private Long category2Id;

    private Long category3Id;

    private String smallPic;

    private BigDecimal price;

    private Long typeTemplateId;

    private String isEnableSpec;

    private String isDelete;

    /* -----------------以下为商品详情信息-------------------------*/
    private GoodsDescVO goodsDescVO;

    /* ============ 对应规格信息===============*/
    private List<ItemVO> itemVOList;

}
