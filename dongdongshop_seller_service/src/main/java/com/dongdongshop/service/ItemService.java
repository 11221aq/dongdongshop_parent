package com.dongdongshop.service;

import com.dongdongshop.vo.ItemVO;

import java.util.List;

public interface ItemService {
    void updateStockCount(List<ItemVO> itemVOList);
}
