package com.dongdongshop.controller;

import com.dongdongshop.data.Result;
import com.dongdongshop.em.ResultEnum;
import com.dongdongshop.service.AssistService;
import com.dongdongshop.vo.AssistVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("assist")
public class AssistController {

    @Autowired
    private AssistService service;

    @PostMapping("getAssistList")
    public Result getAssistList(@RequestParam String pid){
        try {
           List<AssistVO> voList =  service.getAssistList(pid);
           return Result.ok().setData(voList);
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }

    @PostMapping("addAssist")
    public Result addAssist(@RequestBody AssistVO assistVO){
        try {
            service.addAssist(assistVO);
            return Result.ok();
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }

    @PostMapping("getAssistById")
    public Result getAssistById(@RequestParam Integer aid){
        try {
            AssistVO vo =  service.getAssistById(aid);
            return Result.ok().setData(vo);
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }

    @PostMapping("updateAssist")
    public Result updateAssist(@RequestBody AssistVO assistVO){
        try {
            service.updateAssist(assistVO);
            return Result.ok();
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }

    @PostMapping("deleteById")
    public Result deleteById(@RequestParam Integer aid){
        try {
            service.deleteById(aid);
            return Result.ok();
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }

}
