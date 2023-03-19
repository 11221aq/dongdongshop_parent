package com.dongdongshop.controller;

import com.dongdongshop.data.Result;
import com.dongdongshop.em.ResultEnum;
import com.dongdongshop.model.CodePart;
import com.dongdongshop.service.CodePartService;
import com.dongdongshop.vo.CodePartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("codePart")
public class CodePartController {

    @Autowired
    private CodePartService service;

    @PostMapping("getCodePartList")
    public Result getCodePartList(@RequestBody CodePartVO vo){
        try {
            List<CodePartVO> partList = service.getgetCodePartList(vo);
            return Result.ok().setData(partList);
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }

    @PostMapping("addCodePart")
    public Result addCodePart(@RequestBody CodePartVO vo){
        try {
            service.addCodePart(vo);
            return Result.ok();
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }

    @PostMapping("getCodePartById")
    public Result getCodePartById(@RequestParam Long partId){
        try {
            CodePartVO vo = service.getCodePartById(partId);
            return Result.ok().setData(vo);
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }

    @PostMapping("updateCodePart")
    public Result updateCodePart(@RequestBody CodePartVO vo){
        try {
            vo.setUpdateTime(new Date());
            service.updateCodePart(vo);
            return Result.ok();
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }

    @PostMapping("deleteCodePartById")
    public Result deleteCodePartById(@RequestParam Long partId){
        try {
            service.deleteCodePartById(partId);
            return Result.ok();
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }

}
