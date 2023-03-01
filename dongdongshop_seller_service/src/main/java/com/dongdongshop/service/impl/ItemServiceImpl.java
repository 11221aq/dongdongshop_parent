package com.dongdongshop.service.impl;

import com.dongdongshop.mapper.TbItemMapper;
import com.dongdongshop.model.TbItem;
import com.dongdongshop.service.ItemService;
import com.dongdongshop.vo.ItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Resource
    private TbItemMapper itemMapper;

    @Override
    public void updateStockCount(List<ItemVO> itemVOList) {
        for (ItemVO itemVO : itemVOList) {
            TbItem tbItem = itemMapper.selectByPrimaryKey(itemVO.getId());
            tbItem.setStockCount(tbItem.getStockCount() - itemVO.getUpdateNum());
            itemMapper.updateByPrimaryKeySelective(tbItem);
        }
    }
}
