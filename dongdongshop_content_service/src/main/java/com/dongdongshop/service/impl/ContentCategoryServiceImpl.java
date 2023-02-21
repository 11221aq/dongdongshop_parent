package com.dongdongshop.service.impl;

import com.dongdongshop.mapper.TbContentCategoryMapper;
import com.dongdongshop.model.TbContentCategory;
import com.dongdongshop.model.TbContentCategoryExample;
import com.dongdongshop.service.ContentCateGoryService;
import com.dongdongshop.vo.ContentCategoryVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContentCategoryServiceImpl implements ContentCateGoryService {

    @Resource
    private TbContentCategoryMapper contentCategoryMapper;

    @Override
    public List<ContentCategoryVO> getContentList(String name) {
        TbContentCategoryExample example = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = example.createCriteria();
        if(StringUtils.isNotBlank(name)) {
            criteria.andNameLike("%" + name + "%");
        }
        List<TbContentCategory> tbContentCategories = contentCategoryMapper.selectByExample(example);
        List<ContentCategoryVO> collect = tbContentCategories.stream().map(c -> {
            ContentCategoryVO vo = new ContentCategoryVO();
            BeanUtils.copyProperties(c, vo);
            return vo;
        }).collect(Collectors.toList());
        return collect;
    }

    @Override
    public void addContent(ContentCategoryVO vo) {
        TbContentCategory contentCategory  = new TbContentCategory();
        BeanUtils.copyProperties(vo, contentCategory);
        contentCategoryMapper.insertSelective(contentCategory);
    }

    @Override
    public ContentCategoryVO getContent(Long id) {
        TbContentCategory contentCategory = contentCategoryMapper.selectByPrimaryKey(id);
        ContentCategoryVO vo = new ContentCategoryVO();
        BeanUtils.copyProperties(contentCategory, vo);
        return vo;
    }

    @Override
    public void putContent(ContentCategoryVO vo) {
        TbContentCategory contentCategory  = new TbContentCategory();
        BeanUtils.copyProperties(vo, contentCategory);
        contentCategoryMapper.updateByPrimaryKeySelective(contentCategory);
    }

    @Override
    public void deleteAll(Long[] ids) {
        TbContentCategoryExample example = new TbContentCategoryExample();
        example.createCriteria().andIdIn(Arrays.asList(ids));
        contentCategoryMapper.deleteByExample(example);
    }
}
