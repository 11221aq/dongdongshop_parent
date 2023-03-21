package com.dongdongshop.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dongdongshop.data.Result;
import com.dongdongshop.em.ResultEnum;
import com.dongdongshop.model.Workstation;
import com.dongdongshop.service.WorkstationService;
import com.dongdongshop.vo.WorkstationVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("workstation")
public class WorkstationController {

    @Autowired
    private WorkstationService service;


    @PostMapping("getWorkList")
    public Result getWorkList(@RequestBody WorkstationVO vo){
        try {
            List<WorkstationVO> collect = service.list(new LambdaQueryWrapper<Workstation>()
                    .like(vo.getWorkcode() != null, Workstation::getWorkcode, vo.getWorkcode())
                    .like(vo.getWorkname() != null, Workstation::getWorkname, vo.getWorkname())
                    .eq(vo.getFarmid() != null, Workstation::getFarmid, vo.getFarmid())
                    .eq(vo.getOid() != null, Workstation::getOid, vo.getOid())
            ).stream().map(w -> {
                WorkstationVO v = new WorkstationVO();
                BeanUtils.copyProperties(w, v);
                return v;
            }).collect(Collectors.toList());
            return Result.ok().setData(collect);
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }

    @PostMapping("addWork")
    public Result addWork(@RequestBody WorkstationVO vo){
        try {
            Workstation v = new Workstation();
            BeanUtils.copyProperties(vo, v);
            service.save(v);
            return Result.ok();
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }

    @PostMapping("updateWork")
    public Result updateWork(@RequestBody WorkstationVO vo){
        try {
            Workstation v = new Workstation();
            BeanUtils.copyProperties(vo, v);
            service.updateById(v);
            return Result.ok();
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }

    @PostMapping("getWorkById")
    public Result getWorkById(@RequestParam Long id){
        try {
            Workstation byId = service.getById(id);
            WorkstationVO v = new WorkstationVO();
            BeanUtils.copyProperties(byId,v);
            return Result.ok().setData(v);
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }

    @PostMapping("deleteWork")
    public Result deleteWork(@RequestParam Long id){
        try {
            service.removeById(id);
            return Result.ok();
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }



}
