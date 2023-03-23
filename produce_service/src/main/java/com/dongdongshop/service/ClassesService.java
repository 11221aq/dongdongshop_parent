package com.dongdongshop.service;

import com.dongdongshop.model.Classes;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dongdongshop.model.SetType;
import com.dongdongshop.vo.SetTypeVO;

/**
* @author 29555
* @description 针对表【classes】的数据库操作Service
* @createDate 2023-03-23 18:27:48
*/
public interface ClassesService extends IService<Classes> {

    void insertDate(SetType s);
}
