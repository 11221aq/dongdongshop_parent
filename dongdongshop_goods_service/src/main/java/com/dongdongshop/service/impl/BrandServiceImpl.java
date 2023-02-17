package com.dongdongshop.service.impl;

import com.dongdongshop.mapper.TbBrandMapper;
import com.dongdongshop.model.TbBrand;
import com.dongdongshop.model.TbBrandExample;
import com.dongdongshop.service.BrandService;
import com.dongdongshop.util.PageParms;
import com.dongdongshop.util.PageUtil;
import com.dongdongshop.vo.TbBrandVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl implements BrandService {

    @Resource
    private TbBrandMapper brandMapper;

    @Override
    public PageUtil<TbBrandVO> getBrandList(PageParms<TbBrandVO> pageParms) {
        TbBrandVO brandVO = pageParms.getParms();
        TbBrandExample be = new TbBrandExample();
        TbBrandExample.Criteria criteria = be.createCriteria();
        if (StringUtils.isNotBlank(brandVO.getFirstChar())) {
            criteria.andFirstCharEqualTo(brandVO.getFirstChar());
        }
        if (StringUtils.isNotBlank(brandVO.getName())){
            criteria.andNameLike("%"+brandVO.getName()+"%");
        }
        PageHelper.startPage(pageParms.getPageNum(),pageParms.getPageSize());
        List<TbBrand> tbBrands = brandMapper.selectByExample(be);

        PageInfo<TbBrand> pageInfo = new PageInfo<>(tbBrands);
        List<TbBrandVO> collect = pageInfo.getList().stream().map(brand -> {
            TbBrandVO vo = new TbBrandVO();
            BeanUtils.copyProperties(brand, vo);
            return vo;
        }).collect(Collectors.toList());
        return new PageUtil<TbBrandVO>(pageInfo.getPageNum(),pageInfo.getPageSize(),pageInfo.getTotal(),collect,pageInfo.getPages());
    }

    @Override
    public void saveBrand(TbBrandVO tbBrandVO) {
        TbBrand brand = new TbBrand();
        BeanUtils.copyProperties(tbBrandVO,brand);
        brandMapper.insertSelective(brand);
    }

    @Override
    public TbBrandVO getBrandById(Long id) {
        TbBrand brand = brandMapper.selectByPrimaryKey(id);
        TbBrandVO vo = new TbBrandVO();
        BeanUtils.copyProperties(brand,vo);
        return vo;
    }

    @Override
    public void updateBrand(TbBrandVO tbBrandVO) {
        TbBrand brand = new TbBrand();
        BeanUtils.copyProperties(tbBrandVO,brand);
        brandMapper.updateByPrimaryKeySelective(brand);
    }

    @Override
    public void deleteAll(Long[] ids) {
        TbBrandExample be = new TbBrandExample();
        be.createCriteria().andIdIn(Arrays.asList(ids));
        brandMapper.deleteByExample(be);
    }

    public List<TbBrandVO> getBrandNoPage() {
        List<TbBrand> tbBrands = brandMapper.selectByExample(null);
        List<TbBrandVO> brandList = tbBrands.stream().map(brand -> {
            TbBrandVO vo = new TbBrandVO();
            BeanUtils.copyProperties(brand,vo);
            return vo;
        }).collect(Collectors.toList());
        return brandList;
    }

}
