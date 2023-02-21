package com.dongdongshop.service.impl;

import com.dongdongshop.mapper.TbContentMapper;
import com.dongdongshop.model.TbContent;
import com.dongdongshop.service.ContentService;
import com.dongdongshop.vo.ContentVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContentServiceImpl implements ContentService {

    @Resource
    private TbContentMapper contentMapper;

    @Override
    public List<ContentVO> getContentList() {
        List<TbContent> tbContents = contentMapper.selectByExample(null);
        List<ContentVO> collect = tbContents.stream().map(c -> {
            ContentVO vo = new ContentVO();
            BeanUtils.copyProperties(c, vo);
            return vo;
        }).collect(Collectors.toList());
        return collect;
    }
}
