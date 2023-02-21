package com.dongdongshop.service;

import com.dongdongshop.util.PageParms;
import com.dongdongshop.util.PageUtil;
import com.dongdongshop.vo.CommodityReviewVO;
import com.dongdongshop.vo.GoodsVO;
import com.dongdongshop.vo.SaveVO;

public interface GoodsService {
    void saveGoods(String zhuqifa,SaveVO saveVO);

    PageUtil getGoodsList(PageParms<GoodsVO> pageParms);

    void deleteAll(Long[] ids);

    void updateGoods(CommodityReviewVO vo);
}
