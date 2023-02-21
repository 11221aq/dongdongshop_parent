package com.dongdongshop.service;

import com.dongdongshop.vo.ContentCategoryVO;

import java.util.List;

public interface ContentCateGoryService {
    List<ContentCategoryVO> getContentList(String name);

    void addContent(ContentCategoryVO vo);

    ContentCategoryVO getContent(Long id);

    void putContent(ContentCategoryVO vo);

    void deleteAll(Long[] ids);
}
