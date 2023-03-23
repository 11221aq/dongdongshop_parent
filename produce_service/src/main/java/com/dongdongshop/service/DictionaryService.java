package com.dongdongshop.service;

import com.dongdongshop.model.Dictionary;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dongdongshop.vo.DictionaryVO;

import java.util.Date;
import java.util.List;

/**
* @author 29555
* @description 针对表【dictionary(字典)】的数据库操作Service
* @createDate 2023-03-23 15:51:00
*/
public interface DictionaryService extends IService<Dictionary> {

    List<DictionaryVO> getDictionary(DictionaryVO vo, Date endTime);

    void updateDictionary(DictionaryVO vo);

    void deleteById(Long id);
}
