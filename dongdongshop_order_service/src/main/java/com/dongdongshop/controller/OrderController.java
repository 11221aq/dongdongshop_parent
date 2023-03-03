package com.dongdongshop.controller;

import com.dongdongshop.data.Result;
import com.dongdongshop.em.ResultEnum;
import com.dongdongshop.service.OrderService;
import com.dongdongshop.vo.AlipayTradeVO;
import com.dongdongshop.vo.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("submitOrder")
    public Result submitOrder(@RequestHeader String zhuqifa, @RequestParam Long addressId,@RequestParam String payType) {
        try {
            AlipayTradeVO vo = orderService.submitOrder(zhuqifa, addressId, payType);
            return Result.ok().setData(vo);
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e);
        }
    }

    @PostMapping("updateOrderStatus")
    public Result updateOrderStatus(@RequestHeader String out_trade_no, @RequestParam String trade_no){
        try {
            orderService.updateOrderStatus(out_trade_no, trade_no);
            return Result.ok();
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e);
        }
    }

    @PostMapping("getOrderByUserId")
    public Result getOrderByUserId(@RequestHeader String zhuqifa, String status){
        try {
            List<OrderVO> list =  orderService.getOrderByUserId(zhuqifa,status);
            return Result.ok().setData(list);
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e);
        }
    }

    @PostMapping("getOrderById")
    public Result getOrderById(@RequestParam Long id){
        try {
            orderService.getOrderById(id);
            return Result.ok();
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e);
        }
    }

}
