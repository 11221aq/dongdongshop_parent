package com.dongdongshop.service;

import com.dongdongshop.model.SetType;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dongdongshop.vo.SetTypeVO;

import java.util.List;

/**
* @author 29555
* @description 针对表【set_type(排班计划)】的数据库操作Service
* @createDate 2023-03-23 18:01:12
*/
public interface SetTypeService extends IService<SetType> {

    List<SetTypeVO> getSetTypeList(SetTypeVO vo);

    void addSetType(SetTypeVO vo);

    SetTypeVO getSetTypeById(Long id);
}
