package com.dongdongshop.controller;

import com.alibaba.fastjson.JSONObject;
import com.dongdongshop.data.Result;
import com.dongdongshop.data.UserVo;
import com.dongdongshop.em.ResultEnum;
import com.dongdongshop.service.ProduceService;
import com.dongdongshop.vo.ProduceVO;
import com.dongdongshop.vo.ProductVO;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("produce")
public class ProduceController {

    @Autowired
    private ProduceService produceService;

    //查询全部
    @PostMapping("getProduceList")
    public Result getProduceList(@RequestBody ProduceVO vo){
        try {
            List<ProduceVO> produceVOList  = produceService.getProduceList(vo);
            return Result.ok().setData(produceVOList);
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }

    @PostMapping("addProduce")
    public Result addProduce(@RequestBody ProduceVO vo){
        try {
            produceService.addProduce(vo);
            return Result.ok();
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }

    @PostMapping("getProduceById")
    public Result getProduceById(@RequestParam String pid){
        try {
            ProduceVO vo = produceService.getProduceById(pid);
            return Result.ok().setData(vo);
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }

    @PostMapping("updateProduce")
    public Result updateProduce(@RequestBody ProduceVO vo){
        try {
            produceService.updateProduce(vo);
            return Result.ok();
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }

    @PostMapping("deleteProduce")
    public Result deleteProduce(@RequestParam String pid){
        try {
            produceService.deleteProduce(pid);
            return Result.ok();
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }




    @PostMapping("getProListByPid")
    public Result getProListByPid(@RequestParam Long pid) {
        try {
            List<ProductVO> list = produceService.getProListByPid(pid);
            return Result.ok().setData(list);
        }catch (Exception e) {
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }


}
