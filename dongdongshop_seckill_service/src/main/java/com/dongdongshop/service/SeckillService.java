package com.dongdongshop.service;

import com.dongdongshop.vo.SeckillGoodsVO;

import java.util.List;

public interface SeckillService {
    void cachePreHot();

    List<SeckillGoodsVO> getSeckillList();

    SeckillGoodsVO getGoodsById(Long goodsId);

    String submitSeckill(String userName ,Long goodsId);

    void seckillJob();
}
