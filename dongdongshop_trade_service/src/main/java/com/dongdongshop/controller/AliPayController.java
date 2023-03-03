package com.dongdongshop.controller;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayTradeFastpayRefundQueryRequest;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeFastpayRefundQueryResponse;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.dongdongshop.data.Result;
import com.dongdongshop.em.ResultEnum;
import com.dongdongshop.vo.AlipayQueryRefundVO;
import com.dongdongshop.vo.AlipayTradeRefundVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Controller
@RequestMapping("aliPay")
@RefreshScope
public class AliPayController {

    @Autowired
    private AlipayClient alipayClient;

    @Value("${aliPay.apiUrl}")
    private String apiUrl;

    @PostMapping("pagePay")
    @ResponseBody
    public String aliPay(@RequestParam String out_trade_no,@RequestParam String total_amount,@RequestParam String subject) throws AlipayApiException {
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        request.setNotifyUrl(apiUrl + "/aliPayCallBack/notifyUrl");
        request.setReturnUrl(apiUrl + "/aliPayCallBack/returnUrl");
        JSONObject bizContent = new JSONObject();
        bizContent.put("out_trade_no", out_trade_no);
        bizContent.put("total_amount", total_amount);
        bizContent.put("subject", subject);
        bizContent.put("product_code", "FAST_INSTANT_TRADE_PAY");
        request.setBizContent(bizContent.toString());
        AlipayTradePagePayResponse response = alipayClient.pageExecute(request);
        if(response.isSuccess()){
            System.out.println("调用成功");
        } else {
            System.out.println("调用失败");
        }
        return response.getBody();
    }

    @PostMapping("queryTrade")
    @ResponseBody
    public Result queryTrade(@RequestParam String out_trade_no){
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        JSONObject bizContent = new JSONObject();
        bizContent.put("out_trade_no", out_trade_no);
        request.setBizContent(bizContent.toString());
        AlipayTradeQueryResponse response = null;
        try {
            response = alipayClient.execute(request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
            return Result.error();
        }
        if(response.isSuccess()){
            System.out.println("调用成功");
            String body = response.getBody();
            JSONObject object = JSONObject.parseObject(body);
            return Result.ok().setData(object);
        } else {
            System.out.println("调用失败");
            return Result.error();
        }
    }

    @PostMapping("tradeRefund")
    @ResponseBody
    public Result tradeRefund(@RequestParam String trade_no,@RequestParam String refund_amount,@RequestParam String out_request_no){
        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
        JSONObject bizContent = new JSONObject();
        bizContent.put("trade_no", trade_no);//支付宝交易号
        bizContent.put("refund_amount", refund_amount);//退款金额
        bizContent.put("out_request_no", out_request_no);//商户订单号
        request.setBizContent(bizContent.toString());
        AlipayTradeRefundResponse response = null;
        try {
            response = alipayClient.execute(request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
            return Result.result(ResultEnum.REFUND_ERROR);
        }
        if(response.isSuccess()){
            System.out.println("调用成功");//调用成功不代表退款成功
            String body = response.getBody();
            JSONObject jsonObject = JSONObject.parseObject(body);
                                                                                                                                                    AlipayTradeRefundVO refundVO = JSONObject.parseObject(JSONObject.toJSONString(jsonObject.get("alipay_trade_refund_response")), AlipayTradeRefundVO.class);
            if(!Objects.equals(refundVO.getFund_change(),"Y")){//不等
                //需要调用退款查询
                AlipayQueryRefundVO refundQuery = refundQuery(trade_no, out_request_no);
                if(!Objects.equals(refundQuery.getRefund_status(),"REFUND_SUCCESS")){
                    return Result.result(ResultEnum.REFUND_ERROR);
                }
                return Result.result(ResultEnum.REFUND_SUCCESS);
            }
            return Result.result(ResultEnum.REFUND_SUCCESS);
        } else {
            System.out.println("调用失败");
            return Result.result(ResultEnum.REFUND_ERROR);
        }
    }

    private AlipayQueryRefundVO refundQuery(String trade_no, String out_request_no){
        AlipayTradeFastpayRefundQueryRequest request = new AlipayTradeFastpayRefundQueryRequest();
        JSONObject bizContent = new JSONObject();
        bizContent.put("trade_no", trade_no);
        bizContent.put("out_request_no", out_request_no);
        request.setBizContent(bizContent.toString());
        AlipayTradeFastpayRefundQueryResponse response = null;
        try {
            response = alipayClient.execute(request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
            return null;
        }
        if(response.isSuccess()){
            System.out.println("调用成功");
            String body = response.getBody();
            JSONObject jsonObject = JSONObject.parseObject(body);
            AlipayQueryRefundVO refund_query = JSONObject.parseObject(JSONObject.toJSONString(jsonObject.get("alipay_trade_fastpay_refund_query_response")), AlipayQueryRefundVO.class);
            return refund_query;
        } else {
            return null;
        }
    }

}
