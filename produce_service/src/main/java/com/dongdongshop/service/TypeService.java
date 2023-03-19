package com.dongdongshop.service;

import com.dongdongshop.model.Type;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dongdongshop.vo.TypeVO;

import java.util.List;

/**
* @author 29555
* @description 针对表【type】的数据库操作Service
* @createDate 2023-03-17 19:41:28
*/
public interface TypeService extends IService<Type> {

    List<TypeVO> getList();
}
