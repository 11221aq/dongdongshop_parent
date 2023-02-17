package com.dongdongshop.service;

import com.dongdongshop.util.PageParms;
import com.dongdongshop.util.PageUtil;
import com.dongdongshop.vo.TbItemCatVO;

import java.util.Set;

public interface ItemService {
    PageUtil<TbItemCatVO> getItemList(PageParms<TbItemCatVO> parms);

    Set<TbItemCatVO> getItemNameById(Long id);

    void addItem(TbItemCatVO itemCatVO);

    TbItemCatVO getItemById(Long id);

    void updateItem(TbItemCatVO itemCatVO);

    void deleteAll(Long[] ids);
}
