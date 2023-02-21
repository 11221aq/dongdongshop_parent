package com.dongdongshop.vo;

import lombok.Data;

import java.util.List;
@Data
public class SaveVO {
    private GoodsVO goodsVO;
    private GoodsDescVO goodsDescVO;
    private List<ItemVO> itemVOList;
}
