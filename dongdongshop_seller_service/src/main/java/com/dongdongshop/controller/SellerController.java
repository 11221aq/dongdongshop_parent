package com.dongdongshop.controller;

import com.dongdongshop.data.Result;
import com.dongdongshop.em.ResultEnum;
import com.dongdongshop.service.SellerService;
import com.dongdongshop.vo.SellerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("seller")
public class SellerController {

    @Autowired
    private SellerService sellerService;

    @PostMapping
    public Result getSellerList(@RequestBody SellerVO sellerVO){
        try {
            List<SellerVO> sellerVOList =  sellerService.getSellerList(sellerVO);
            return Result.result(ResultEnum.SUCCESS).setData(sellerVOList);
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e);
        }
    }

    @GetMapping
    public Result getSellerById(@RequestParam String sellerId){
        try {
            SellerVO sellerVO =  sellerService.getSellerById(sellerId);
            return Result.result(ResultEnum.SUCCESS).setData(sellerVO);
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e);
        }
    }

    @PutMapping
    public Result updateStatus(@RequestBody SellerVO sellerVO){
        try {
            sellerService.updateStatus(sellerVO);
            return Result.ok();
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e);
        }
    }

    //判断是否存在名字id

    @GetMapping("checkName")
    public Result checkName(@RequestParam String sellerId){
        try {
            boolean b = sellerService.checkName(sellerId);
            return Result.ok().setData(b);
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e);
        }
    }

    @PostMapping("addSeller")
    public Result addSeller(@RequestBody SellerVO sellerVO){
        try {
            sellerService.addSeller(sellerVO);
            return Result.ok();
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e);
        }
    }

}
