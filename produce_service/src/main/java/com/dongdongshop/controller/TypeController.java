package com.dongdongshop.controller;

import com.dongdongshop.data.Result;
import com.dongdongshop.em.ResultEnum;
import com.dongdongshop.service.TypeService;
import com.dongdongshop.vo.TypeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("type")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @PostMapping("getList")
    public Result getList() {
        try {
            List<TypeVO>  typeList = typeService.getList();
            return Result.ok().setData(typeList);

        }catch (Exception e) {
            e.printStackTrace();
            return Result.error().setData(ResultEnum.ERROR);
        }
    }

}
