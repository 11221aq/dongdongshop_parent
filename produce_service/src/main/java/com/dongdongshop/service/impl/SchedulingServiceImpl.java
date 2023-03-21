package com.dongdongshop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dongdongshop.model.Scheduling;
import com.dongdongshop.service.*;
import com.dongdongshop.mapper.SchedulingMapper;
import com.dongdongshop.vo.ProduceVO;
import com.dongdongshop.vo.SchedulingVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author 29555
* @description 针对表【scheduling(排产)】的数据库操作Service实现
* @createDate 2023-03-21 12:17:16
*/
@Service
public class SchedulingServiceImpl extends ServiceImpl<SchedulingMapper, Scheduling>
    implements SchedulingService{

    @Resource
    private SchedulingMapper mapper;

    @Resource
    private CodeService codeService;

    @Resource
    private ProduceService produceService;

    @Override
    public void addScheduling(SchedulingVO schedulingVO) {
        Scheduling s = new Scheduling();
        BeanUtils.copyProperties(schedulingVO,s);
        s.setWorkcode(codeService.getCode(221L));//设置编码
        ProduceVO produceVO = produceService.getProduceById(schedulingVO.getPid());
        s.setWorkname(produceVO.getProductName() + produceVO.getOrderNum() + produceVO.getUnit());//名称
        mapper.insert(s);
    }

    @Override
    public void updateScheduling(SchedulingVO schedulingVO) {
        Scheduling s = new Scheduling();
        BeanUtils.copyProperties(schedulingVO,s);
        mapper.updateById(s);
    }



    //查询list

}




