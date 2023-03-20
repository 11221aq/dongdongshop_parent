package com.dongdongshop.controller;

import com.dongdongshop.data.Result;
import com.dongdongshop.em.ResultEnum;
import com.dongdongshop.service.TechnologyService;
import com.dongdongshop.vo.TechnologyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("technology")
public class TechnologyController {

    @Autowired
    private TechnologyService service;

    @PostMapping("getTechnologyList")
    public Result getTechnologyList(@RequestBody TechnologyVO vo){
        try {
           List<TechnologyVO> list = service.getTechnologyList(vo);
            return Result.ok().setData(list);
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }

    @PostMapping("addTechnology")
    public Result addTechnology(@RequestBody TechnologyVO vo){
        try {
            service.addTechnology(vo);
            return Result.ok();
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }

    @PostMapping("getTechnicalById")
    public Result getTechnicalById(@RequestParam Long tid){
        try {
            TechnologyVO vo = service.getTechnicalById(tid);
            return Result.ok().setData(vo);
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }

    @PostMapping("updateTechnology")
    public Result updateTechnology(@RequestBody TechnologyVO vo){
        try {
            service.updateTechnology(vo);
            return Result.ok();
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }

    //TODO:删除时把关联的底部表删除

    @PostMapping("deleteTechnical")
    public Result deleteTechnical(@RequestParam Long tid){
        try {
            service.deleteTechnical(tid);
            return Result.ok();
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }

}
