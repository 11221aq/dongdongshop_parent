package com.dongdongshop.service;

import com.dongdongshop.data.UserVo;
import com.dongdongshop.vo.AlipayTradeVO;
import com.dongdongshop.vo.OrderVO;

import java.util.List;

public interface OrderService {
    AlipayTradeVO submitOrder(String zhuqifa, Long addressId, String payType);

    void updateOrderStatus(String out_trade_no, String trade_no);

    List<OrderVO> getOrderByUserId(String zhuqifa,String status);

    void getOrderById(Long id);
}
