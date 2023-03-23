package com.dongdongshop.controller;

import com.dongdongshop.data.Result;
import com.dongdongshop.em.ResultEnum;
import com.dongdongshop.model.Reporting;
import com.dongdongshop.service.ReportingService;
import com.dongdongshop.vo.ReportingVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("reporting")
@CrossOrigin
public class ReportingController {

    @Autowired
    private ReportingService service;

    @PostMapping("getReportingList")
    public Result getReportingList(@RequestBody ReportingVO reportingVO) {
        try {
            List<ReportingVO> voList = service.getReportingList(reportingVO);
            return Result.ok().setData(voList);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }

    @PostMapping("addReporting")
    public Result addReporting(@RequestBody ReportingVO vo) {
        try {
            service.addReporting(vo);
            return Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }

    @PostMapping("getById")
    public Result getById(@RequestParam Long id) {
        try {
            Reporting byId = service.getById(id);
            ReportingVO vo = new ReportingVO();
            BeanUtils.copyProperties(byId, vo);
            return Result.ok().setData(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }

    @PostMapping("updateReporting")
    public Result updateReporting(@RequestBody ReportingVO vo) {
        try {
            service.updateReporting(vo);
            return Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }

    @PostMapping("deleteById")
    public Result deleteById(@RequestParam Long id) {
        try {
            service.removeById(id);
            return Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }

    @PostMapping("getTU")
    public Result getTU() {
        try {
            Map<String,Object> tu = service.getTU();
            return Result.ok().setData(tu);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }

}
