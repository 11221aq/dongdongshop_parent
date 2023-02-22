package com.dongdongshop.service;

import com.dongdongshop.vo.ContentStatusVO;
import com.dongdongshop.vo.ContentVO;

import java.util.List;

public interface ContentService {
    List<ContentVO> getContentList();

    void addContent(ContentVO contentVO);

    ContentVO getContentById(Long id);

    void updateContent(ContentVO contentVO);

    void deleteAll(Long[] ids);

    void updateStatus(ContentStatusVO statusVO);

    List<ContentVO> getContentImage(Long categoryId);
}
