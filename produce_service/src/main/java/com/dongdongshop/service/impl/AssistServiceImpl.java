package com.dongdongshop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dongdongshop.model.Assist;
import com.dongdongshop.service.AssistService;
import com.dongdongshop.mapper.AssistMapper;
import com.dongdongshop.vo.AssistVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author 29555
* @description 针对表【assist】的数据库操作Service实现
* @createDate 2023-03-18 09:12:59
*/
@Service
public class AssistServiceImpl extends ServiceImpl<AssistMapper, Assist>
    implements AssistService{

    @Resource
    private AssistMapper mapper;

    @Override
    public List<AssistVO> getAssistList(String pid) {
        return null;
    }
}




