package com.dongdongshop.filter;

import com.dongdongshop.data.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "dongdongshop-order-service")
public interface OrderServiceApi {

    @PostMapping("/order/updateOrderStatus")
    Result updateOrderStatus(@RequestHeader String out_trade_no, @RequestParam String trade_no);
}
