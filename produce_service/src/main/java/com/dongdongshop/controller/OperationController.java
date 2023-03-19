package com.dongdongshop.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dongdongshop.data.Result;
import com.dongdongshop.em.ResultEnum;
import com.dongdongshop.service.OperationService;
import com.dongdongshop.util.PageParms;
import com.dongdongshop.vo.OperationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("operation")
public class OperationController {

    @Autowired
    private OperationService service;

    @PostMapping("getOperationList")
    public Result getOperationList(@RequestBody PageParms<OperationVO> parms) {
        try {
            Page<OperationVO> page = service.getOperationList(parms);
            return Result.ok().setData(page);
        }catch (Exception e) {
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }

    @PostMapping("addOperation")
    public Result addOperation(@RequestBody OperationVO vo) {
        try {
            service.addOperation(vo);
            return Result.ok();
        }catch (Exception e) {
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }

    @PostMapping("deleteOperation")
    public Result deleteOperation(@RequestParam String oid){
        try {
            service.deleteOperation(oid);
            return Result.ok();
        }catch (Exception e) {
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }

}
