package com.dongdongshop.service;

import com.dongdongshop.model.Reporting;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dongdongshop.vo.ReportingVO;

import java.util.List;
import java.util.Map;

/**
* @author 29555
* @description 针对表【reporting(生产报工)】的数据库操作Service
* @createDate 2023-03-21 22:03:09
*/
public interface ReportingService extends IService<Reporting> {

    List<ReportingVO> getReportingList(ReportingVO reportingVO);

    void addReporting(ReportingVO vo);

    void updateReporting(ReportingVO vo);

    Map<String,Object> getTU();
}
