package com.dongdongshop.controller;

import com.dongdongshop.data.Result;
import com.dongdongshop.em.ResultEnum;
import com.dongdongshop.model.DictionaryDesc;
import com.dongdongshop.service.DictionaryDescService;
import com.dongdongshop.vo.DictionaryDescVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("dictionaryDesc")
public class DictionaryDescController {

    @Autowired
    private DictionaryDescService service;

    @PostMapping("getDescList")
    public Result getDescList(@RequestBody DictionaryDescVO vo){
        try {
            List<DictionaryDescVO> voList = service.getDescList(vo);
            return Result.ok().setData(voList);
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }

    @PostMapping("addDesc")
    public Result addDesc(@RequestBody DictionaryDescVO vo) {
        try {
            DictionaryDesc d = new DictionaryDesc();
            BeanUtils.copyProperties(vo,d);
            service.save(d);
            return Result.ok();
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }

    @PostMapping("updateDesc")
    public Result updateDesc(@RequestBody DictionaryDescVO vo) {
        try {
            DictionaryDesc d = new DictionaryDesc();
            BeanUtils.copyProperties(vo,d);
            service.updateById(d);
            return Result.ok();
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }

    @PostMapping("getById")
    public Result getById(@RequestParam Long id) {
        try {
            DictionaryDesc desc = service.getById(id);
            DictionaryDescVO vo = new DictionaryDescVO();
            BeanUtils.copyProperties(desc, vo);
            return Result.ok().setData(vo);
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }

    @PostMapping("deleteById")
    public Result deleteById(@RequestParam Long id) {
        try {
           service.removeById(id);
            return Result.ok();
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }

}
