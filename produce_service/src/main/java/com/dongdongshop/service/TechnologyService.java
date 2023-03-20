package com.dongdongshop.service;

import com.dongdongshop.model.Technology;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dongdongshop.vo.TechnologyVO;

import java.util.List;

/**
* @author 29555
* @description 针对表【technology(工艺表)】的数据库操作Service
* @createDate 2023-03-20 11:57:29
*/
public interface TechnologyService extends IService<Technology> {

    List<TechnologyVO> getTechnologyList(TechnologyVO vo);

    void addTechnology(TechnologyVO vo);

    TechnologyVO getTechnicalById(Long tid);

    void updateTechnology(TechnologyVO vo);

    void deleteTechnical(Long tid);
}
