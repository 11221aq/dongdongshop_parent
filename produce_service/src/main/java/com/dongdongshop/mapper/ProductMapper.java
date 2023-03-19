package com.dongdongshop.mapper;

import com.dongdongshop.model.Product;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 29555
* @description 针对表【product】的数据库操作Mapper
* @createDate 2023-03-17 11:20:02
* @Entity com.dongdongshop.model.Product
*/
@Mapper
public interface ProductMapper extends BaseMapper<Product> {

}




