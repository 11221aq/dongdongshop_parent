package com.dongdongshop.mapper;

import com.dongdongshop.model.Customer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 29555
* @description 针对表【customer】的数据库操作Mapper
* @createDate 2023-03-17 19:56:45
* @Entity com.dongdongshop.model.Customer
*/
@Mapper
public interface CustomerMapper extends BaseMapper<Customer> {

}




