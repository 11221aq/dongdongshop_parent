package com.dongdognshop.service;

import com.dongdognshop.vo.ItemVO;
import com.dongdongshop.util.PageParms;
import com.dongdongshop.util.PageUtil;
import com.dongdongshop.util.SearchParms;

public interface SearchService {
    PageUtil<ItemVO> searchList(PageParms pageParms);
}
