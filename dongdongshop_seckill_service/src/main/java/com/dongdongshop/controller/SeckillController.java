package com.dongdongshop.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.dongdongshop.data.Result;
import com.dongdongshop.em.ResultEnum;
import com.dongdongshop.service.SeckillService;
import com.dongdongshop.vo.SeckillGoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("seckill")
@CrossOrigin
public class SeckillController extends BaseController {

    @Autowired
    private SeckillService seckillService;

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

}
