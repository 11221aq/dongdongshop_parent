package com.dongdongshop.controller;

import com.dongdongshop.data.Result;
import com.dongdongshop.em.ResultEnum;
import com.dongdongshop.service.BrandService;
import com.dongdongshop.util.PageParms;
import com.dongdongshop.util.PageUtil;
import com.dongdongshop.vo.TbBrandVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @PostMapping
    public Result getBrandList(@RequestBody PageParms<TbBrandVO> pageParms) {
       try {
           PageUtil<TbBrandVO> page = brandService.getBrandList(pageParms);
           return Result.ok().setData(page);
       }catch (Exception e){
           e.printStackTrace();
           return Result.result(ResultEnum.ERROR).setData(e);
       }
    }

    @PostMapping("saveBrand")
    public Result saveBrand(@RequestBody TbBrandVO tbBrandVO) {
        try {
            brandService.saveBrand(tbBrandVO);
            return Result.result(ResultEnum.SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e);
        }
    }

    @GetMapping
    public Result updateBrand(@RequestParam Long id) {
        try {
            TbBrandVO brandById = brandService.getBrandById(id);
            return Result.result(ResultEnum.SUCCESS).setData(brandById);
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e);
        }
    }

    @PutMapping
    public Result updateBrand(@RequestBody TbBrandVO tbBrandVO) {
        try {
            brandService.updateBrand(tbBrandVO);
            return Result.result(ResultEnum.SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e);
        }
    }
    @DeleteMapping
    public Result deleteAll(@RequestBody Long[] ids) {
        try {
            brandService.deleteAll(ids);
            return Result.result(ResultEnum.SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e);
        }
    }

}
