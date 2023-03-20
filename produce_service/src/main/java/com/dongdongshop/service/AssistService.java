package com.dongdongshop.service;

import com.dongdongshop.model.Assist;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dongdongshop.vo.AssistVO;

import java.util.List;

/**
* @author 29555
* @description 针对表【assist(辅助)】的数据库操作Service
* @createDate 2023-03-20 16:46:48
*/
public interface AssistService extends IService<Assist> {

    List<AssistVO> getAssistList(String pid);

    void addAssist(AssistVO assistVO);

    AssistVO getAssistById(Integer aid);

    void updateAssist(AssistVO assistVO);

    void deleteById(Integer aid);
    void deleteByOid(String oid);
}
