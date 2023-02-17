package com.dongdongshop.service;

import com.dongdongshop.util.PageParms;
import com.dongdongshop.util.PageUtil;
import com.dongdongshop.vo.TbBrandVO;

public interface BrandService {
    PageUtil<TbBrandVO> getBrandList(PageParms<TbBrandVO> pageParms);

    void saveBrand(TbBrandVO tbBrandVO);

    TbBrandVO getBrandById(Long id);

    void updateBrand(TbBrandVO tbBrandVO);

    void deleteAll(Long[] ids);
}
