package com.dongdongshop.controller;

import com.dongdongshop.data.Result;
import com.dongdongshop.em.ResultEnum;
import com.dongdongshop.service.TecctService;
import com.dongdongshop.vo.TecctVO;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tecct")
public class TecctController {

    @Autowired
    private TecctService service;

    @PostMapping("addDate")
    public Result addDate(@RequestBody TecctVO vo) {
        try {
            service.addDate(vo);
            return Result.ok();
        }catch (Exception e) {
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }

    @PostMapping("updateDate")
    public Result updateDate(@RequestBody TecctVO vo){
        try {
            service.updateDate(vo);
            return Result.ok();
        }catch (Exception e) {
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }

    @PostMapping("deleteDate")
    public Result deleteDate(@RequestParam String productid){
        try {
            service.deleteDate(productid);
            return Result.ok();
        }catch (Exception e) {
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }


}
