package com.dongdongshop.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dongdongshop.data.Result;
import com.dongdongshop.em.ResultEnum;
import com.dongdongshop.model.TeamsSet;
import com.dongdongshop.service.TeamsSetService;
import com.dongdongshop.vo.TeamsSetVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("teamsSet")
public class TeamsSetController {

    @Autowired
    private TeamsSetService setService;

    @PostMapping("addTeamSet")
    public Result addTeamSet(@RequestBody TeamsSetVO vo){
        try {
            TeamsSet one = setService.getOne(new LambdaQueryWrapper<TeamsSet>()
                    .eq(vo.getTid() != null, TeamsSet::getTid, vo.getTid())
            );
            if(one != null){
                throw new RuntimeException("班组以存在");
            }
            TeamsSet ts = new TeamsSet();
            BeanUtils.copyProperties(vo, ts);
            setService.save(ts);
            return Result.ok();
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }

    @PostMapping("deleteByTid")
    public Result deleteByTid(@RequestParam Long tid){
        try {
            setService.remove(new LambdaQueryWrapper<TeamsSet>()
                .eq(TeamsSet::getTid,tid)
            );
            return Result.ok();
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }

}
