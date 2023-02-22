package com.dongdongshop.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.dongdongshop.mapper.TbContentMapper;
import com.dongdongshop.model.TbContent;
import com.dongdongshop.model.TbContentExample;
import com.dongdongshop.service.ContentService;
import com.dongdongshop.vo.ContentStatusVO;
import com.dongdongshop.vo.ContentVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContentServiceImpl implements ContentService {

    @Resource
    private TbContentMapper contentMapper;

    @Resource
    private RedisTemplate redisTemplate;

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
        redisTemplate.delete("count");
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
        redisTemplate.delete("count");
    }

    @Override
    public void deleteAll(Long[] ids) {
        TbContentExample example = new TbContentExample();
        example.createCriteria().andIdIn(Arrays.asList(ids));
        contentMapper.deleteByExample(example);
        redisTemplate.delete("count");
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
        redisTemplate.delete("count");
    }

    @Override
    public List<ContentVO> getContentImage(Long categoryId) {
        String str = (String)redisTemplate.boundHashOps("content").get("image_" + categoryId);
        if(StringUtils.isNotBlank(str)){
            List<ContentVO> contentVOList = JSONObject.parseArray(str, ContentVO.class);
            return contentVOList;
        }
        TbContentExample example = new TbContentExample();
        example.setOrderByClause("sort_order asc");
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(categoryId);
        criteria.andStatusEqualTo("1");
        List<TbContent> tbContents = contentMapper.selectByExample(example);
        List<ContentVO> collect = tbContents.stream().map(c -> {
            ContentVO vo = new ContentVO();
            BeanUtils.copyProperties(c, vo);
            return vo;
        }).collect(Collectors.toList());
        redisTemplate.boundHashOps("content").put("image_" + categoryId, JSONObject.toJSONString(collect));
        return collect;
    }
}
