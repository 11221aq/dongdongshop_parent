package com.dongdongshop.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dongdongshop.model.Product;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dongdongshop.util.MyPage;
import com.dongdongshop.util.PageParms;
import com.dongdongshop.vo.ProductVO;

import java.util.List;

/**
* @author 29555
* @description 针对表【product】的数据库操作Service
* @createDate 2023-03-17 11:20:02
*/
public interface ProductService extends IService<Product> {

    MyPage<ProductVO> getProductList(PageParms<ProductVO> parms);

    MyPage<ProductVO> getListByPid(PageParms<ProductVO> parms);

    List<Product> getProduct(List<String> list);
}
