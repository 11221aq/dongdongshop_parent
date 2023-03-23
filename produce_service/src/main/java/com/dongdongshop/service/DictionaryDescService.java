package com.dongdongshop.service;

import com.dongdongshop.model.DictionaryDesc;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dongdongshop.vo.DictionaryDescVO;

import java.util.List;

/**
* @author 29555
* @description 针对表【dictionary_desc(字典明细表)】的数据库操作Service
* @createDate 2023-03-23 15:51:05
*/
public interface DictionaryDescService extends IService<DictionaryDesc> {

    List<DictionaryDescVO> getDescList(DictionaryDescVO vo);
}
