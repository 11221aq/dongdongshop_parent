package com.dongdongshop.service.impl;

import com.dongdongshop.mapper.TbBrandMapper;
import com.dongdongshop.mapper.TbSpecificationMapper;
import com.dongdongshop.mapper.TbTypeTemplateMapper;
import com.dongdongshop.model.TbTypeTemplate;
import com.dongdongshop.model.TbTypeTemplateExample;
import com.dongdongshop.service.TypeService;
import com.dongdongshop.util.PageUtil;
import com.dongdongshop.vo.TbTypeTemplateVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TypeServiceImpl implements TypeService {

    @Resource
    private TbTypeTemplateMapper typeMapper;

    @Resource
    private TbBrandMapper brandMapper;

    @Resource
    private TbSpecificationMapper specMapper;

    @Override
    public Map<String ,Object> geTypeList(Integer pageNum, Integer pageSize, String name) {
        com.github.pagehelper.Page<Object> os = PageHelper.startPage(pageNum, pageSize);
        Map<String, Object> map = new HashMap<>();
        TbTypeTemplateExample example = new TbTypeTemplateExample();
        if(StringUtils.isNotBlank(name)){
            example.createCriteria().andNameLike("%" + name + "%");
        }
        List<TbTypeTemplate> typeList = typeMapper.selectByExample(example);
        List<TbTypeTemplateVO> collect = typeList.stream().map(t -> {
            TbTypeTemplateVO vo = new TbTypeTemplateVO();
            BeanUtils.copyProperties(t, vo);
            return vo;
        }).collect(Collectors.toList());
        PageInfo<TbTypeTemplateVO> info = new PageInfo<>(collect);
        info.setTotal(os.getTotal());
        info.setPageNum(os.getPageNum());
        info.setPageSize(os.getPageSize());
        info.setPages(os.getPages());
        map.put("page",new PageUtil<TbTypeTemplateVO>(info.getPageNum(),info.getPageSize(),info.getTotal(),info.getList(),info.getPages()));

        map.put("brandList",brandMapper.selectByExample(null));
        map.put("specList",specMapper.selectByExample(null));
        return map;
    }

    @Override
    public void save(TbTypeTemplateVO typeTemplateVO) {
        TbTypeTemplate tbTypeTemplate = new TbTypeTemplate();
        BeanUtils.copyProperties(typeTemplateVO, tbTypeTemplate);
        typeMapper.insertSelective(tbTypeTemplate);
    }

    @Override
    public TbTypeTemplateVO getTypeById(Long id) {
        TbTypeTemplateVO vo = new TbTypeTemplateVO();
        TbTypeTemplate tbTypeTemplate = typeMapper.selectByPrimaryKey(id);
        BeanUtils.copyProperties(tbTypeTemplate, vo);
        return vo;
    }

    @Override
    public void updateType(TbTypeTemplateVO typeTemplateVO) {
        TbTypeTemplate template = new TbTypeTemplate();
        BeanUtils.copyProperties(typeTemplateVO, template);
        typeMapper.updateByPrimaryKeySelective(template);
    }


    @Override
    public void deleteAll(Long[] ids) {
        TbTypeTemplateExample example = new TbTypeTemplateExample();
        example.createCriteria().andIdIn(Arrays.asList(ids));
        typeMapper.deleteByExample(example);
    }
}
