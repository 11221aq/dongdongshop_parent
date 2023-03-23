package com.dongdongshop.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dongdongshop.data.Result;
import com.dongdongshop.em.ResultEnum;
import com.dongdongshop.model.Scheduling;
import com.dongdongshop.service.SchedulingService;
import com.dongdongshop.vo.ProduceVO;
import com.dongdongshop.vo.SchedulingVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("scheduling")
public class SchedulingController {

    @Autowired
    private SchedulingService service;

    @PostMapping("getSchedulingList")
    public Result getSchedulingList(@RequestBody SchedulingVO vo){
        try {
            List<SchedulingVO> collect = service.list(new LambdaQueryWrapper<Scheduling>()
                    .eq(vo.getPid() != null,Scheduling::getPid, vo.getPid())
                    .eq(vo.getProcessid() != null,Scheduling::getProcessid, vo.getProcessid())
                    .eq(vo.getWorkcode() != null,Scheduling::getWorkcode, vo.getWorkcode())
                    .eq(vo.getAsscode() != null,Scheduling::getAsscode, vo.getAsscode())
            ).stream().map(s -> {
                SchedulingVO v = new SchedulingVO();
                BeanUtils.copyProperties(s, v);
                return v;
            }).collect(Collectors.toList());
            return Result.ok().setData(collect);
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }

    @PostMapping("addScheduling")
    public Result addScheduling(@RequestBody SchedulingVO schedulingVO){
        try {
            service.addScheduling(schedulingVO);
            return Result.ok();
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }

    @PostMapping("getById")
    public  Result getById(@RequestParam Long id){
        try {
            Scheduling byId = service.getById(id);
            SchedulingVO schedulingVO = new SchedulingVO();
            BeanUtils.copyProperties(byId, schedulingVO);
            return Result.ok().setData(schedulingVO);
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }

    @PostMapping("updateScheduling")
    public Result updateScheduling(@RequestBody SchedulingVO schedulingVO){
        try {
            service.updateScheduling(schedulingVO);
            return Result.ok();
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }

    @PostMapping("deleteScheduling")
    public Result deleteScheduling(@RequestParam Long id){
        try {
            service.removeById(id);
            return Result.ok();
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }

    @PostMapping("getFigure")
    public Result getFigure(){
        try {
            List<ProduceVO> figure = service.getFigure();
            return Result.ok().setData(figure);
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }

}
