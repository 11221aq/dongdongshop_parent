package com.dongdongshop.controller;

import com.dongdongshop.data.Result;
import com.dongdongshop.em.ResultEnum;
import com.dongdongshop.service.DepartmentService;
import com.dongdongshop.vo.DepartmentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("department")
public class DepartmentController {

    @Autowired
    private DepartmentService service;

    @PostMapping("getDepartmentList")
    public Result getDepartmentList(@RequestBody DepartmentVO vo) {
        try {
            List<DepartmentVO> voList =  service.getDepartmentList(vo);
            return Result.ok().setData(voList);
        }catch (Exception e) {
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }

    @PostMapping("updateDepartment")
    public Result getDepartment(@RequestBody DepartmentVO vo){
        try {
            service.getDepartment(vo);
            return Result.ok();
        }catch (Exception e) {
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }

    @PostMapping("addDepartment")
    public Result addDepartment(@RequestBody DepartmentVO vo){
        try {
            service.addDepartment(vo);
            return Result.ok();
        }catch (Exception e) {
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }

    @PostMapping("getDepartmentById")
    public Result getDepartmentById(@RequestParam Integer id){
        try {
            DepartmentVO vo = service.getDepartmentById(id);
            return Result.ok();
        }catch (Exception e) {
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }

    @PostMapping("deleteById")
    public Result deleteById(@RequestParam Integer id){
        try {
            service.deleteById(id);
            return Result.ok();
        }catch (Exception e) {
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }

}
