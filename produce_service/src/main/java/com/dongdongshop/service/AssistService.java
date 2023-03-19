package com.dongdongshop.service;

import com.dongdongshop.model.Assist;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dongdongshop.vo.AssistVO;

import java.util.List;

/**
* @author 29555
* @description 针对表【assist】的数据库操作Service
* @createDate 2023-03-18 09:12:59
*/
public interface AssistService extends IService<Assist> {

    List<AssistVO> getAssistList(String pid);
}
