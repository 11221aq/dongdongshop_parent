package com.dongdongshop.controller;

import com.dongdongshop.data.Result;
import com.dongdongshop.em.ResultEnum;
import com.dongdongshop.service.SpecService;
import com.dongdongshop.util.PageParms;
import com.dongdongshop.util.PageUtil;
import com.dongdongshop.vo.SpecVO;
import com.dongdongshop.vo.TbSpecificationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("spec")
public class SpecController {

    @Autowired
    private SpecService specService;

    @PostMapping
    public Result getSpecificationList(@RequestBody PageParms<SpecVO> pageParms){
        try {
            PageUtil<TbSpecificationVO> page = specService.getSpecificationList(pageParms);
            return Result.result(ResultEnum.SUCCESS).setData(page);
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e);
        }
    }

    @PostMapping("save")
    private Result save(@RequestBody SpecVO specVO){
        try {
            specService.save(specVO);
            return Result.result(ResultEnum.SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e);
        }
    }

    @GetMapping
    private Result updateTrim(Long id){
        try {
            SpecVO specVO = specService.updateTrim(id);
            return Result.ok().setData(specVO);
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e);
        }
    }

    @PutMapping
    private Result updateSave(@RequestBody SpecVO specVO){
        try {
            specService.updateSave(specVO);
            return Result.result(ResultEnum.SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e);
        }
    }

    @DeleteMapping
    public Result deleteAll(@RequestBody Long[] ids){
        try {
            specService.deleteAll(ids);
            return Result.result(ResultEnum.SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e);
        }
    }

}
