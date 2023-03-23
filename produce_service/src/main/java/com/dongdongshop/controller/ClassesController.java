package com.dongdongshop.controller;

import com.dongdongshop.data.Result;
import com.dongdongshop.em.ResultEnum;
import com.dongdongshop.model.Classes;
import com.dongdongshop.service.ClassesService;
import com.dongdongshop.vo.ClassesVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("classes")
public class ClassesController {

    @Autowired
    private ClassesService service;

    @PostMapping("addClasses")
    private Result addClasses(@RequestBody ClassesVO vo) {
        try {
            Classes c = new Classes();
            BeanUtils.copyProperties(vo,c);
            service.save(c);
            return Result.ok();
        }catch (Exception e) {
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }

    @PostMapping("deleteById")
    public Result deleteById(@RequestParam Long id) {
        try {
            service.removeById(id);
            return Result.ok();
        }catch (Exception e) {
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }

}
