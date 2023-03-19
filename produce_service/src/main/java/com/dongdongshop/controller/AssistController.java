package com.dongdongshop.controller;

import com.dongdongshop.data.Result;
import com.dongdongshop.em.ResultEnum;
import com.dongdongshop.service.AssistService;
import com.dongdongshop.vo.AssistVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("assist")
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

}
