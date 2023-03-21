package com.dongdongshop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dongdongshop.model.Farm;
import com.dongdongshop.service.FarmService;
import com.dongdongshop.mapper.FarmMapper;
import org.springframework.stereotype.Service;

/**
* @author 29555
* @description 针对表【farm(车间)】的数据库操作Service实现
* @createDate 2023-03-21 11:11:17
*/
@Service
public class FarmServiceImpl extends ServiceImpl<FarmMapper, Farm>
    implements FarmService{

}




