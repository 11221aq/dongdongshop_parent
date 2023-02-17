package com.dongdongshop.controller;

import com.dongdongshop.data.Result;
import com.dongdongshop.em.ResultEnum;
import com.dongdongshop.service.TypeService;
import com.dongdongshop.vo.TbTypeTemplateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("type")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @PostMapping
    public Result geTypeList(@RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10")Integer pageSize,@RequestBody TbTypeTemplateVO typeVO){
        try {
            Map<String ,Object> map = typeService.geTypeList(pageNum,pageSize,typeVO.getName());
            return Result.result(ResultEnum.SUCCESS).setData(map);
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e);
        }
    }

    @PostMapping("save")
    public Result save(@RequestBody TbTypeTemplateVO typeTemplateVO){
        try {
            typeService.save(typeTemplateVO);
            return Result.result(ResultEnum.SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e);
        }
    }

    @GetMapping
    public Result getTypeById(@RequestParam Long id){
        try {
            TbTypeTemplateVO vo = typeService.getTypeById(id);
            return Result.result(ResultEnum.SUCCESS).setData(vo);
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e);
        }
    }

    @PutMapping
    public Result updateType(@RequestBody TbTypeTemplateVO typeTemplateVO){
        try {
            typeService.updateType(typeTemplateVO);
            return Result.result(ResultEnum.SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e);
        }
    }

    @DeleteMapping
    public Result deleteAll(@RequestBody Long[] ids){
        try {
            typeService.deleteAll(ids);
            return Result.result(ResultEnum.SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e);
        }
    }


}
