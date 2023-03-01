package com.dongdongshop.service;

import com.dongdongshop.data.UserVo;

public interface OrderService {
    void submitOrder(String zhuqifa, Long addressId, String payType);
}
