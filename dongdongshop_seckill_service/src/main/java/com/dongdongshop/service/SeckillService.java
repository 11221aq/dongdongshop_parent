package com.dongdongshop.service;

import com.dongdongshop.vo.SeckillGoodsVO;

import java.util.List;

public interface SeckillService {
    void cachePreHot();

    List<SeckillGoodsVO> getSeckillList();
}
