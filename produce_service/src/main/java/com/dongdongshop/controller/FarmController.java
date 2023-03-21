package com.dongdongshop.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dongdongshop.data.Result;
import com.dongdongshop.em.ResultEnum;
import com.dongdongshop.model.Farm;
import com.dongdongshop.service.FarmService;
import com.dongdongshop.vo.FarmVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("farm")
public class FarmController {

    @Autowired
    private FarmService service;

    @PostMapping("getFarmList")
    public Result getFarmList(@RequestBody FarmVO farmVO) {
        try {
            List<FarmVO> collect = service.list(new LambdaQueryWrapper<Farm>()
                    .like(farmVO.getFcode() != null, Farm::getFcode, farmVO.getFcode())
                    .like(farmVO.getFname() != null, Farm::getFname, farmVO.getFname())
            ).stream().map(f -> {
                FarmVO v = new FarmVO();
                BeanUtils.copyProperties(f, v);
                return v;
            }).collect(Collectors.toList());
            return Result.ok().setData(collect);
        }catch (Exception e) {
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }

    @PostMapping("addFarm")
    public Result addFarm(@RequestBody FarmVO vo) {
        try {
            Farm v = new Farm();
            BeanUtils.copyProperties(vo, v);
            service.save(v);
            return Result.ok();
        }catch (Exception e) {
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }


    @PostMapping("updateFarm")
    public Result updateFarm(@RequestBody FarmVO vo) {
        try {
            Farm v = new Farm();
            BeanUtils.copyProperties(vo, v);
            service.updateById(v);
            return Result.ok();
        }catch (Exception e) {
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }

    @PostMapping("getFarmById")
    public Result getFarmById(@RequestParam Long id) {
        try {
            Farm byId = service.getById(id);
            FarmVO v = new FarmVO();
            BeanUtils.copyProperties(byId, v);
            return Result.ok().setData(v);
        }catch (Exception e) {
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }

    @PostMapping("deleteFarm")
    public Result deleteFarm(@RequestParam Long id) {
        try {
           service.removeById(id);
            return Result.ok();
        }catch (Exception e) {
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }



}

