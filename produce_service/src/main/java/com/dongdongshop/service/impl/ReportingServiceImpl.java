package com.dongdongshop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dongdongshop.model.Reporting;
import com.dongdongshop.model.Scheduling;
import com.dongdongshop.model.Workstation;
import com.dongdongshop.service.ReportingService;
import com.dongdongshop.mapper.ReportingMapper;
import com.dongdongshop.service.SchedulingService;
import com.dongdongshop.service.WorkstationService;
import com.dongdongshop.vo.ReportingVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
* @author 29555
* @description 针对表【reporting(生产报工)】的数据库操作Service实现
* @createDate 2023-03-21 22:03:09
*/
@Service
public class ReportingServiceImpl extends ServiceImpl<ReportingMapper, Reporting>
    implements ReportingService{

    @Resource
    private ReportingMapper mapper;

    @Resource
    private SchedulingService schedulingService;

    @Resource
    private WorkstationService service;


    @Override
    public List<ReportingVO> getReportingList(ReportingVO reportingVO) {
        List<ReportingVO> collect = mapper.selectList(new LambdaQueryWrapper<Reporting>()
                .like(reportingVO.getType() != null, Reporting::getType, reportingVO.getType())
                .like(reportingVO.getWorkName() != null, Reporting::getWorkName, reportingVO.getWorkName())
                .like(reportingVO.getProduceId() != null, Reporting::getProduceId, reportingVO.getProduceId())
                .like(reportingVO.getProductId() != null, Reporting::getProductId, reportingVO.getProductId())
                .like(reportingVO.getProductName() != null, Reporting::getProductName, reportingVO.getProductName())
                .like(reportingVO.getProductName() != null, Reporting::getProductName, reportingVO.getProductName())
                .like(reportingVO.getReporter() != null, Reporting::getReporter, reportingVO.getReporter())
                .like(reportingVO.getReviewed() != null, Reporting::getReviewed, reportingVO.getReviewed())
                .eq(reportingVO.getStatus() != null, Reporting::getStatus, reportingVO.getStatus())
        ).stream().map(r -> {
            ReportingVO v = new ReportingVO();
            BeanUtils.copyProperties(r, v);
            return v;
        }).collect(Collectors.toList());
        return collect;
    }

    @Override
    public void addReporting(ReportingVO vo) {
        Reporting r = new Reporting();
        BeanUtils.copyProperties(vo, r);
        r.setSumNum(r.getDefectsNum() + r.getQualifiedNum());
        mapper.insert(r);

    }

    @Override
    public void updateReporting(ReportingVO vo) {
        Reporting r = new Reporting();
        BeanUtils.copyProperties(vo,r);
        mapper.updateById(r);
        if(r.getStatus() == 4){ //已完成
            schedulingService.updateNum(r.getSumNum(),r.getSchedulingId());
        }
    }

    @Override
    public Map<String,Object> getTU() {

        Map<String,Object> returnMap = new HashMap<String,Object>();

        Map<String, List<Reporting>> collect = mapper.selectList(null).stream().collect(Collectors.groupingBy(Reporting::getWorkName));
        List<Map<String,Object>> mapList = new ArrayList<>();
        List<Workstation> workstationList = service.list();//获取工作名
        List<String> workName = new ArrayList<>();

        List<Long> certificateList = new ArrayList<>();
        List<Long> badList = new ArrayList<>();

        for (Workstation workstation : workstationList) {

            workName.add(workstation.getWorkname());

            Long sum = 0L;

            Long certificateNum = 0L;//合格数

            Long badNum = 0L;// 不合格数


            List<Reporting> reportingList = collect.get(workstation.getWorkname());
            if (reportingList == null){
                continue;
            }
            for (Reporting reporting : reportingList) {
                sum += reporting.getSumNum();

                certificateNum += reporting.getQualifiedNum();
                badNum += reporting.getDefectsNum();
            }

            Map<String,Object> map = new HashMap<String,Object>();
            map.put("name",workstation.getWorkname());
            map.put("value",sum);
            if(!mapList.contains(map)) {
                mapList.add(map);
            }

            certificateList.add(certificateNum);
            badList.add(badNum);
        }

        Map<String,Object> qualified = new HashMap<>();

        Map<String, Object> m1 =  new HashMap<String, Object>();
        m1.put("show",true);
        Map<String, Object> m2 =  new HashMap<String, Object>();
        m2.put("focus","series");

        qualified.put("name","合格数");
        qualified.put("type","bar");
        qualified.put("stack","total");
        qualified.put("label",m1);
        qualified.put("emphasis",m2);
        qualified.put("data",certificateList);

        Map<String,Object> unqualified = new HashMap<>();
        unqualified.put("name","不合格数");
        unqualified.put("type","bar");
        unqualified.put("stack","total");
        unqualified.put("label",m1);
        unqualified.put("emphasis",m2);
        unqualified.put("data",badList);

        List<Map<String, Object>> histogram = new ArrayList<>();
        histogram.add(qualified);
        histogram.add(unqualified);

        returnMap.put("circular",mapList);
        returnMap.put("histogram",histogram);
        returnMap.put("workName",workName);

        return returnMap;
    }

}




