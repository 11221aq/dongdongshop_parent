package com.dongdongshop.controller;

import com.dongdongshop.data.Result;
import com.dongdongshop.em.ResultEnum;
import com.dongdongshop.service.CodeRuleService;
import com.dongdongshop.vo.CodeRuleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("codeRule")
public class CodeRuleController {

    @Autowired
    private CodeRuleService service;

    @PostMapping("getCodeRuleList")
    public Result getCodeRuleList(@RequestBody CodeRuleVO codeRuleVO){
        try {
            List<CodeRuleVO> list = service.getCodeRuleList(codeRuleVO);
            return Result.ok().setData(list);
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }

    @PostMapping("addCodeRule")
    public Result addCodeRule(@RequestBody CodeRuleVO code){
        try {
            service.addCodeRule(code);
            return Result.ok();
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }

    @PostMapping("getCodeRuleById")
    public Result getCodeRuleById(@RequestParam Long ruleId){
        try {
            CodeRuleVO vo = service.getCodeRuleById(ruleId);
            return Result.ok().setData(vo);
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }

    @PostMapping("updateCodeRule")
    public Result updateCodeRule(@RequestBody CodeRuleVO code){
        try {
            service.updateCodeRule(code);
            return Result.ok();
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }

    @PostMapping("deleteCudeRule")
    public Result deleteCudeRule(@RequestParam Long ruleId){
        try {
            service.deleteCudeRule(ruleId);
            return Result.ok();
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }

}
