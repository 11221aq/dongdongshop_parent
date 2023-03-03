package com.dongdongshop.filter;

import com.dongdongshop.data.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(value = "dongdongshop-trade-service")
public interface TradeServiceApi {
    @PostMapping("/aliPay/tradeRefund")
    @ResponseBody
    Result tradeRefund(@RequestParam String trade_no, @RequestParam String refund_amount, @RequestParam String out_request_no);
}
