package com.dongdongshop.service;

import com.dongdongshop.model.Scheduling;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dongdongshop.vo.SchedulingVO;

import java.util.List;

/**
* @author 29555
* @description 针对表【scheduling(排产)】的数据库操作Service
* @createDate 2023-03-21 12:17:16
*/
public interface SchedulingService extends IService<Scheduling> {

    void addScheduling(SchedulingVO schedulingVO);

    void updateScheduling(SchedulingVO schedulingVO);
}
