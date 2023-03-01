package com.dongdongshop.service;

import com.dongdongshop.vo.CartVO;

import java.util.List;

public interface CartService {
    void addCart(String userId, Long itemId, Integer num);

    List<CartVO> getRedisCartVO(String userId);
}
