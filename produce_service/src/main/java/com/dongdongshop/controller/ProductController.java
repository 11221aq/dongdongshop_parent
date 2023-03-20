package com.dongdongshop.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dongdongshop.data.Result;
import com.dongdongshop.em.ResultEnum;
import com.dongdongshop.model.Product;
import com.dongdongshop.service.ProductService;
import com.dongdongshop.util.MyPage;
import com.dongdongshop.util.PageParms;
import com.dongdongshop.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("getProductList")
    public Result getProductList(@RequestBody PageParms<ProductVO> parms){
        try {
            MyPage<ProductVO> voList = productService.getProductList(parms);
            return Result.ok().setData(voList);
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }


    @PostMapping("getListByPid")
    public Result getListByPid(@RequestBody PageParms<ProductVO> parms){
        try {
            MyPage<ProductVO> voList = productService.getListByPid(parms);
            return Result.ok().setData(voList);
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }


}
