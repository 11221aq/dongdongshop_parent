package com.dongdongshop.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.dongdongshop.mapper.TbSpecificationMapper;
import com.dongdongshop.mapper.TbSpecificationOptionMapper;
import com.dongdongshop.model.TbSpecification;
import com.dongdongshop.model.TbSpecificationExample;
import com.dongdongshop.model.TbSpecificationOption;
import com.dongdongshop.model.TbSpecificationOptionExample;
import com.dongdongshop.service.SpecService;
import com.dongdongshop.util.PageParms;
import com.dongdongshop.util.PageUtil;
import com.dongdongshop.vo.SpecVO;
import com.dongdongshop.vo.TbSpecificationOptionVO;
import com.dongdongshop.vo.TbSpecificationVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpecServiceImpl implements SpecService {

    @Resource
    private TbSpecificationMapper specificationMapper;

    @Resource
    private TbSpecificationOptionMapper optionMapper;

    @Override
    public PageUtil<TbSpecificationVO> getSpecificationList(PageParms<SpecVO> pageParms) {
        SpecVO specVO = pageParms.getParms();
        com.github.pagehelper.Page<Object> os = PageHelper.startPage(pageParms.getPageNum(), pageParms.getPageSize());
        TbSpecificationExample example = new TbSpecificationExample();
        TbSpecificationExample.Criteria criteria = example.createCriteria();
        if(StringUtils.isNotBlank(specVO.getSpecName())){
            criteria.andSpecNameLike("%" + specVO.getSpecName() + "%");
        }
        List<TbSpecification> tbSpecifications = specificationMapper.selectByExample(example);
        List<TbSpecificationVO> collect = tbSpecifications.stream().map(spec -> {
            TbSpecificationVO vo = new TbSpecificationVO();
            BeanUtils.copyProperties(spec, vo);
            return vo;
        }).collect(Collectors.toList());
        PageInfo<TbSpecificationVO> pageInfo = new PageInfo<TbSpecificationVO>(collect);
        pageInfo.setTotal(os.getTotal());
        pageInfo.setPageNum(os.getPageNum());
        pageInfo.setPageSize(os.getPageSize());
        pageInfo.setPages(os.getPages());
        return new PageUtil<TbSpecificationVO>(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getTotal(), pageInfo.getList(), pageInfo.getPages());
    }

    @Override
    @Transactional
    public void save(SpecVO specVO) {

        TbSpecification spec = new TbSpecification();
        spec.setSpecName(specVO.getSpec());
        List<TbSpecificationOptionVO> optionList = JSONObject.parseArray(specVO.getOptionList(), TbSpecificationOptionVO.class);

        specificationMapper.insertSelective(spec);
        List<TbSpecificationOption> collect = optionList.stream().map(vo -> {
            TbSpecificationOption option = new TbSpecificationOption();
            option.setOptionName(vo.getOptionName());
            option.setOrders(vo.getOrders());
            option.setSpecId(spec.getId());
            return option;
        }).collect(Collectors.toList());
        for (TbSpecificationOption option : collect) {
            if(option.getOrders() == null || "".equals(option.getOptionName()) || option.getOptionName() == null){
                continue;
            }
            optionMapper.insertSelective(option);
        }
    }

    @Override
    public SpecVO updateTrim(Long id) {
        SpecVO specVO = new SpecVO();
        TbSpecification tbSpecification = specificationMapper.selectByPrimaryKey(id);
        TbSpecificationVO vo =new TbSpecificationVO();
        BeanUtils.copyProperties(tbSpecification, vo);
        specVO.setSpecification(vo);

        TbSpecificationOptionExample example =new TbSpecificationOptionExample();
        TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
        criteria.andSpecIdEqualTo(id);
        List<TbSpecificationOption> tbSpec = optionMapper.selectByExample(example);
        List<TbSpecificationOptionVO> collect = tbSpec.stream().map(t -> {
            TbSpecificationOptionVO optionVO = new TbSpecificationOptionVO();
            BeanUtils.copyProperties(t, optionVO);
            return optionVO;
        }).collect(Collectors.toList());
        specVO.setList(collect);
        return specVO;
    }

    @Override
    @Transactional
    public void updateSave(@RequestBody SpecVO specVO) {

        TbSpecification spec = new TbSpecification();
        spec.setId(specVO.getId());
        spec.setSpecName(specVO.getSpec());
        List<TbSpecificationOptionVO> optionList = JSONObject.parseArray(specVO.getOptionList(), TbSpecificationOptionVO.class);

        specificationMapper.updateByPrimaryKeySelective(spec);

        List<TbSpecificationOption> collect = optionList.stream().map(vo -> {
            TbSpecificationOption option = new TbSpecificationOption();
            BeanUtils.copyProperties(vo, option);
            option.setSpecId(spec.getId());
            return option;
        }).collect(Collectors.toList());

        TbSpecificationOptionExample optionExample = new TbSpecificationOptionExample();
        optionExample.createCriteria().andSpecIdEqualTo(spec.getId());
        optionMapper.deleteByExample(optionExample);

        for (TbSpecificationOption option : collect) {
            if(option.getOrders() == null || "".equals(option.getOptionName()) || option.getOptionName() == null){
                continue;
            }
            optionMapper.insertSelective(option);
        }
    }

    @Override
    @Transactional
    public void deleteAll(Long[] ids) {
        TbSpecificationExample example = new TbSpecificationExample();
        example.createCriteria().andIdIn(Arrays.asList(ids));
        specificationMapper.deleteByExample(example);

        TbSpecificationOptionExample optionExample = new TbSpecificationOptionExample();
        for (Long id : ids) {
            optionExample.createCriteria().andSpecIdEqualTo(id);
            optionMapper.deleteByExample(optionExample);
        }
    }
}
