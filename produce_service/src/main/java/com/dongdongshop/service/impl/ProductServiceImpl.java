package com.dongdongshop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dongdongshop.model.Product;
import com.dongdongshop.service.ProductService;
import com.dongdongshop.mapper.ProductMapper;
import com.dongdongshop.util.MyPage;
import com.dongdongshop.util.PageParms;
import com.dongdongshop.vo.ProductVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author 29555
* @description 针对表【product】的数据库操作Service实现
* @createDate 2023-03-17 11:20:02
*/
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product>
    implements ProductService{

    @Resource
    private ProductMapper productMapper;

    @Override
    public MyPage<ProductVO> getProductList(PageParms<ProductVO> parms) {
        ProductVO productVO = parms.getParms();
        IPage<Product> page = new Page<>(parms.getPageNum(), parms.getPageSize());
        LambdaQueryWrapper<Product> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(productVO.getProductType() != null ,Product :: getProductType,productVO.getProductType());
        wrapper.like(productVO.getProductId() != null ,Product :: getProductId,productVO.getProductId());
        wrapper.like(productVO.getProductName() != null ,Product :: getProductName,productVO.getProductName());
        productMapper.selectPage(page,wrapper);
        List<ProductVO> collect = page.getRecords().stream().map(p -> {
            ProductVO vo = new ProductVO();
            BeanUtils.copyProperties(p, vo);
            return vo;
        }).collect(Collectors.toList());
        MyPage<ProductVO> util = new MyPage(page.getCurrent(),page.getSize(),page.getTotal(),page.getPages(),collect);
        return util;
    }

    @Override
    public MyPage<ProductVO> getListByPid(PageParms<ProductVO> parms) {
        ProductVO productVO = parms.getParms();
        IPage<Product> page = new Page<>(parms.getPageNum(), parms.getPageSize());
        productMapper.selectPage(page,new LambdaQueryWrapper<Product>().eq(productVO.getProductId() != null ,Product :: getProductPid,productVO.getProductId() ));
        List<ProductVO> collect = page.getRecords().stream().map(p -> {
            ProductVO vo = new ProductVO();
            BeanUtils.copyProperties(p, vo);
            return vo;
        }).collect(Collectors.toList());
        MyPage<ProductVO> util = new MyPage(page.getCurrent(),page.getSize(),page.getTotal(),page.getPages(),collect);
        return util;
    }

    @Override
    public List<Product> getProduct(List<String> list) {
        return productMapper.selectBatchIds(list);
    }
}




