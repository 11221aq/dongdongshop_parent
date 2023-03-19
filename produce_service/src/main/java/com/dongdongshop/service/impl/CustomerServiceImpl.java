package com.dongdongshop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dongdongshop.model.Customer;
import com.dongdongshop.service.CustomerService;
import com.dongdongshop.mapper.CustomerMapper;
import com.dongdongshop.util.PageParms;
import com.dongdongshop.vo.CustomerVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author 29555
* @description 针对表【customer】的数据库操作Service实现
* @createDate 2023-03-17 19:56:45
*/
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer>
    implements CustomerService{

    @Resource
    private CustomerMapper customerMapper;

    @Override
    public Page<CustomerVO> getCustomerList(PageParms<CustomerVO> parms) {
        CustomerVO vo = parms.getParms();
        Page page = new Page(parms.getPageNum(), parms.getPageSize());
        LambdaQueryWrapper<Customer> wrapper = new LambdaQueryWrapper<Customer>();
        wrapper.like(vo.getId() != null,Customer::getId,vo.getId());
        wrapper.like(vo.getName() != null,Customer::getName,vo.getName());
        wrapper.like(vo.getShortName() != null,Customer::getShortName,vo.getShortName());
        wrapper.eq(vo.getStatus() != null,Customer::getStatus,vo.getStatus());
        customerMapper.selectPage(page,wrapper);
        Page p = new Page();
        BeanUtils.copyProperties(page,p);
        return p;
    }
}




