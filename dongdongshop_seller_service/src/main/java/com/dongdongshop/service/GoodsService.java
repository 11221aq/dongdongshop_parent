package com.dongdongshop.service;

import com.dongdongshop.util.PageParms;
import com.dongdongshop.util.PageUtil;
import com.dongdongshop.vo.CommodityReviewVO;
import com.dongdongshop.vo.GoodsVO;
import com.dongdongshop.vo.ItemVO;
import com.dongdongshop.vo.SaveVO;

import java.util.List;

public interface GoodsService {
    void saveGoods(String zhuqifa,SaveVO saveVO);

    PageUtil getGoodsList(PageParms<GoodsVO> pageParms);

    void deleteAll(Long[] ids);

    void updateGoods(CommodityReviewVO vo);

    GoodsVO getGoodsById(Long goodsId);

    ItemVO getItemById(Long itemId);
}
