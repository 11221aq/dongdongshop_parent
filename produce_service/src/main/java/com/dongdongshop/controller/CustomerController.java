package com.dongdongshop.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dongdongshop.data.Result;
import com.dongdongshop.em.ResultEnum;
import com.dongdongshop.service.CustomerService;
import com.dongdongshop.util.PageParms;
import com.dongdongshop.vo.CustomerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("getCustomerList")
    public Result getCustomerList(@RequestBody PageParms<CustomerVO> parms){
        try {
            Page<CustomerVO> page = customerService.getCustomerList(parms);
            return Result.ok().setData(page);
        }catch (Exception e){
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }

}
