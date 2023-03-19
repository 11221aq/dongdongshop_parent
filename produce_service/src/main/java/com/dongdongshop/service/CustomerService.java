package com.dongdongshop.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dongdongshop.model.Customer;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dongdongshop.util.PageParms;
import com.dongdongshop.vo.CustomerVO;

/**
* @author 29555
* @description 针对表【customer】的数据库操作Service
* @createDate 2023-03-17 19:56:45
*/
public interface CustomerService extends IService<Customer> {

    Page<CustomerVO> getCustomerList(PageParms<CustomerVO> parms);
}
