package com.dongdongshop.controller;

import com.dongdongshop.data.Result;
import com.dongdongshop.em.ResultEnum;
import com.dongdongshop.service.SetTypeService;
import com.dongdongshop.vo.SetTypeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("setType")
public class SetTypeController {

    @Autowired
    private SetTypeService setTypeService;

    @PostMapping("getSetTypeList")
    public Result getSetTypeList(@RequestBody SetTypeVO vo){
        try {
            List<SetTypeVO> voList = setTypeService.getSetTypeList(vo);
            return Result.ok().setData(voList);
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }

    @PostMapping("addSetType")
    public Result addSetType(@RequestBody SetTypeVO vo){
        try {
            setTypeService.addSetType(vo);
            return Result.ok();
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }

    @PostMapping("getSetTypeById")
    public Result getSetTypeById(@RequestParam Long id){
        try {
            SetTypeVO vo = setTypeService.getSetTypeById(id);
            return Result.ok().setData(vo);
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }

}
