package com.dongdongshop.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.fastjson.JSONObject;
import com.dongdongshop.data.Result;
import com.dongdongshop.data.UserVo;
import com.dongdongshop.em.ResultEnum;
import com.dongdongshop.service.SeckillService;
import com.dongdongshop.util.JwtUtil;
import com.dongdongshop.vo.SeckillGoodsVO;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("seckill")
public class SeckillController extends BaseController {

    @Autowired
    private SeckillService seckillService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("cachePreHot")
    public Result cachePreHot() {
        try {
            seckillService.cachePreHot();
            return Result.ok();
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e);
        }
    }

    @PostMapping("getSeckillList")
    @SentinelResource(value = "getSeckillList",blockHandler = "hotBlockHandler")
    public Result getSeckillList() {
        try {
            List<SeckillGoodsVO> voList =  seckillService.getSeckillList();
            return Result.ok().setData(voList);
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e);
        }
    }

    @PostMapping("getGoodsById")
    public Result getGoodsById(@RequestParam Long goodsId){
        try {
            SeckillGoodsVO goodsVO =  seckillService.getGoodsById(goodsId);
            return Result.ok().setData(goodsVO);
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e);
        }
    }


    @PostMapping("submitSeckill")
    public Result submitSeckill(@RequestHeader String zhuqifa,@RequestParam Long goodsId){
        try {
            Claims claims = jwtUtil.parseJwt(zhuqifa);
            if (claims == null){
                return Result.ok().setData("请先进行登录");
            }
            UserVo userVo = JSONObject.parseObject(claims.getSubject(), UserVo.class);
            String orderId =  seckillService.submitSeckill(userVo.getUsername(),goodsId);
            return Result.ok().setData(orderId);
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }


}
