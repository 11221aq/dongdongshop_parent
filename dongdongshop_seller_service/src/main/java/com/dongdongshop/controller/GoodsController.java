package com.dongdongshop.controller;

import com.dongdongshop.data.Result;
import com.dongdongshop.em.ResultEnum;
import com.dongdongshop.service.GoodsService;
import com.dongdongshop.util.PageParms;
import com.dongdongshop.util.PageUtil;
import com.dongdongshop.vo.CommodityReviewVO;
import com.dongdongshop.vo.GoodsVO;
import com.dongdongshop.vo.ItemVO;
import com.dongdongshop.vo.SaveVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @PostMapping
    public Result<String> saveGoods(@RequestHeader("zhuqifa") String zhuqifa,@RequestBody SaveVO saveVO) {
        try {
            goodsService.saveGoods(zhuqifa,saveVO);
            return Result.ok();
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e);
        }
    }

    @PostMapping("getGoodsList")
    public Result getGoodsList(@RequestBody PageParms<GoodsVO> pageParms) {
        try {
            PageUtil pageUtil = goodsService.getGoodsList(pageParms);
            return Result.ok().setData(pageUtil);
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e);
        }
    }

    @DeleteMapping
    public Result deleteAll(@RequestBody Long[] ids) {
        try {
            goodsService.deleteAll(ids);
            return Result.ok();
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e);
        }
    }

    @PostMapping("updateGoods")
    public Result updateGoods(@RequestBody CommodityReviewVO vo){
        try {
            goodsService.updateGoods(vo);
            return Result.ok();
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e);
        }
    }


    @PostMapping("getGoodsById")
    public Result getGoodsById(@RequestParam Long goodsId){
        try {
            GoodsVO goodsVO = goodsService.getGoodsById(goodsId);
            return Result.ok().setData(goodsVO);
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e);
        }
    }

    @PostMapping("getItemById")
    public Result getItemById(@RequestParam Long itemId){
        try {
            ItemVO itemVO = goodsService.getItemById(itemId);
            return Result.ok().setData(itemVO);
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e);
        }
    }


}
