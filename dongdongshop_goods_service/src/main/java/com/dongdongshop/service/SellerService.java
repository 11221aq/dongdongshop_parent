package com.dongdongshop.service;

import com.dongdongshop.vo.SellerVO;

import java.util.List;

public interface SellerService {
    List<SellerVO> getSellerList(SellerVO sellerVO);

    SellerVO getSellerById(String sellerId);

    void updateStatus(SellerVO sellerVO);
}
