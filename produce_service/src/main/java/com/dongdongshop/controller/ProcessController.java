package com.dongdongshop.controller;

import com.dongdongshop.data.Result;
import com.dongdongshop.em.ResultEnum;
import com.dongdongshop.service.ProcessService;
import com.dongdongshop.vo.ProcessVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("process")
public class ProcessController {

    @Autowired
    private ProcessService service;

    @PostMapping("getProcessList")
    public Result getProcessList(@RequestParam Long tid) {
        try {
            List<ProcessVO> collect = service.getProcessList(tid);
            return Result.ok().setData(collect);
        }catch (Exception e) {
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }

    @PostMapping("addProcess")
    public Result addProcess(@RequestBody ProcessVO vo){
        try {
            service.addProcess(vo);
            return Result.ok();
        }catch (Exception e) {
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }

    @PostMapping("getPrcessById")
    public Result getPrcessById(@RequestParam Long pid){
        try {
            ProcessVO collect = service.getPrcessById(pid);
            return Result.ok().setData(collect);
        }catch (Exception e) {
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }

    @PostMapping("updateProcess")
    public Result updateProcess(@RequestBody ProcessVO vo){
        try {
            service.updateProcess(vo);
            return Result.ok();
        }catch (Exception e) {
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }

    @PostMapping("deleteProcess")
    public Result deleteProcess(@RequestParam Long pid){
        try {
            service.deleteProcess(pid);
            return Result.ok();
        }catch (Exception e) {
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }

}
