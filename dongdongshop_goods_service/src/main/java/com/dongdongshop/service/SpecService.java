package com.dongdongshop.service;

import com.dongdongshop.util.PageParms;
import com.dongdongshop.util.PageUtil;
import com.dongdongshop.vo.SpecVO;
import com.dongdongshop.vo.TbSpecificationVO;
import org.springframework.web.bind.annotation.RequestBody;

public interface SpecService {
    PageUtil<TbSpecificationVO> getSpecificationList(PageParms<SpecVO> pageParms);

    void save(SpecVO specVO);

    SpecVO updateTrim(Long id);

    void updateSave(@RequestBody SpecVO specVO);

    void deleteAll(Long[] ids);
}
