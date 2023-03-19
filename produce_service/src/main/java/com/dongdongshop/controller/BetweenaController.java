package com.dongdongshop.controller;

import com.dongdongshop.data.Result;
import com.dongdongshop.em.ResultEnum;
import com.dongdongshop.service.BetweenaService;
import com.dongdongshop.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("betweena")
public class BetweenaController {

    @Autowired
    private BetweenaService service;

    @PostMapping("getBetList")
    private Result getBetList(@RequestParam String pid){
        try {
            List<ProductVO> betList = service.getBetList(pid);
            return Result.ok().setData(betList);
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }

}
