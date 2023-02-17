package com.dongdongshop.controller;

import com.dongdongshop.data.Result;
import com.dongdongshop.em.ResultEnum;
import com.dongdongshop.service.SellerService;
import com.dongdongshop.util.PageUtil;
import com.dongdongshop.vo.SellerVO;
import com.dongdongshop.vo.TbSpecificationVO;
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

}
