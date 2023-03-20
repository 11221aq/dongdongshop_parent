package com.dongdongshop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dongdongshop.model.Technology;
import com.dongdongshop.service.TecctService;
import com.dongdongshop.service.TechnologyService;
import com.dongdongshop.mapper.TechnologyMapper;
import com.dongdongshop.service.TecproService;
import com.dongdongshop.vo.TechnologyVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author 29555
* @description 针对表【technology(工艺表)】的数据库操作Service实现
* @createDate 2023-03-20 11:57:29
*/
@Service
public class TechnologyServiceImpl extends ServiceImpl<TechnologyMapper, Technology>
    implements TechnologyService{

    @Resource
    private TechnologyMapper mapper;

    @Autowired
    private TecctService tecctService;

    @Autowired
    private TecproService tecproService;

    @Override
    public List<TechnologyVO> getTechnologyList(TechnologyVO vo) {
        LambdaQueryWrapper<Technology> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(vo.getTcode() != null,Technology::getTcode,vo.getTcode());
        wrapper.like(vo.getTname() != null,Technology::getTname,vo.getTname());
        wrapper.like(vo.getStatus() != null,Technology::getStatus,vo.getStatus());
        List<TechnologyVO> collect = mapper.selectList(wrapper).stream().map(t -> {
            TechnologyVO v = new TechnologyVO();
            BeanUtils.copyProperties(t, v);
            return v;
        }).collect(Collectors.toList());
        return collect;
    }

    @Override
    public void addTechnology(TechnologyVO vo) {
        Technology t = new Technology();
        BeanUtils.copyProperties(vo,t);
        mapper.insert(t);
    }

    @Override
    public TechnologyVO getTechnicalById(Long tid) {
        Technology technology = mapper.selectById(tid);
        TechnologyVO v = new TechnologyVO();
        BeanUtils.copyProperties(technology, v);
        return v;
    }

    @Override
    public void updateTechnology(TechnologyVO vo) {
        Technology t = new Technology();
        BeanUtils.copyProperties(vo,t);
        mapper.updateById(t);
    }

    @Override
    @Transactional
    public void deleteTechnical(Long tid) {
        mapper.deleteById(tid);
        tecctService.deleteTecct(tid);
        tecproService.deleteTecpro(tid);
    }
}




