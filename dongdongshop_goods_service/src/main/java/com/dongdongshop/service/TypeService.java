package com.dongdongshop.service;

import com.dongdongshop.vo.TbTypeTemplateVO;

import java.util.Map;

public interface TypeService {
    Map<String ,Object> geTypeList(Integer pageNum, Integer pageSize, String name);

    void save(TbTypeTemplateVO typeTemplateVO);

    TbTypeTemplateVO getTypeById(Long id);

    void updateType(TbTypeTemplateVO typeTemplateVO);

    void deleteAll(Long[] ids);
}
