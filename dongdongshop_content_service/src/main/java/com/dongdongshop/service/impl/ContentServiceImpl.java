package com.dongdongshop.service.impl;

import com.dongdongshop.mapper.TbContentMapper;
import com.dongdongshop.model.TbContent;
import com.dongdongshop.model.TbContentExample;
import com.dongdongshop.service.ContentService;
import com.dongdongshop.vo.ContentStatusVO;
import com.dongdongshop.vo.ContentVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContentServiceImpl implements ContentService {

    @Resource
    private TbContentMapper contentMapper;

    @Override
    public List<ContentVO> getContentList() {
        TbContentExample example = new TbContentExample();
        example.setOrderByClause("sort_order asc");
        List<TbContent> tbContents = contentMapper.selectByExample(example);
        List<ContentVO> collect = tbContents.stream().map(c -> {
            ContentVO vo = new ContentVO();
            BeanUtils.copyProperties(c, vo);
            return vo;
        }).collect(Collectors.toList());
        return collect;
    }

    @Override
    public void addContent(ContentVO contentVO) {
        TbContent content = new TbContent();
        BeanUtils.copyProperties(contentVO, content);
        contentMapper.insertSelective(content);
    }

    @Override
    public ContentVO getContentById(Long id) {
        TbContent c = contentMapper.selectByPrimaryKey(id);
        ContentVO vo = new ContentVO();
        BeanUtils.copyProperties(c, vo);
        return vo;
    }

    @Override
    public void updateContent(ContentVO contentVO) {
        TbContent content = new TbContent();
        BeanUtils.copyProperties(contentVO, content);
        contentMapper.updateByPrimaryKeySelective(content);
    }

    @Override
    public void deleteAll(Long[] ids) {
        TbContentExample example = new TbContentExample();
        example.createCriteria().andIdIn(Arrays.asList(ids));
        contentMapper.deleteByExample(example);
    }

    @Override
    public void updateStatus(ContentStatusVO statusVO) {
        for (Long i : statusVO.getIds()) {
            TbContent tbContent = contentMapper.selectByPrimaryKey(i);
            if(statusVO.getId() == 1){
                tbContent.setStatus("1");
            }else{
                tbContent.setStatus("2");
            }
            contentMapper.updateByPrimaryKeySelective(tbContent);
        }
    }

    @Override
    public List<ContentVO> getContentImage(Long categoryId) {
        TbContentExample example = new TbContentExample();
        example.setOrderByClause("sort_order asc");
        example.createCriteria().andCategoryIdEqualTo(categoryId);
        List<TbContent> tbContents = contentMapper.selectByExample(example);
        List<ContentVO> collect = tbContents.stream().map(c -> {
            ContentVO vo = new ContentVO();
            BeanUtils.copyProperties(c, vo);
            return vo;
        }).collect(Collectors.toList());
        return collect;
    }
}
