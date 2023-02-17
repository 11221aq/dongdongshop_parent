package com.dongdongshop.service.impl;

import com.dongdongshop.data.Result;
import com.dongdongshop.mapper.TbSellerMapper;
import com.dongdongshop.model.TbSeller;
import com.dongdongshop.model.TbSellerExample;
import com.dongdongshop.service.SellerService;
import com.dongdongshop.vo.SellerVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SellerServiceImpl implements SellerService {

    @Resource
    private TbSellerMapper sellerMapper;

    @Override
    public List<SellerVO> getSellerList(SellerVO sellerVO) {
        TbSellerExample example = new TbSellerExample();
        TbSellerExample.Criteria criteria = example.createCriteria();
        if(StringUtils.isNotBlank(sellerVO.getName())){
            criteria.andNameLike("%" + sellerVO.getName() + "%");
        }
        if(StringUtils.isNotBlank(sellerVO.getNickName())){
            criteria.andNameLike("%" + sellerVO.getNickName() + "%");
        }
        if(StringUtils.isNotBlank(sellerVO.getStatus()) && Integer.valueOf(sellerVO.getStatus()) != -1){
            criteria.andStatusEqualTo(sellerVO.getStatus());
        }
        List<TbSeller> tbSellers = sellerMapper.selectByExample(example);
        List<SellerVO> collect = tbSellers.stream().map(s -> {
            SellerVO vo = new SellerVO();
            BeanUtils.copyProperties(s, vo);
            return vo;
        }).collect(Collectors.toList());
        return collect;
    }

    @Override
    public SellerVO getSellerById(String sellerId) {
        TbSellerExample example = new TbSellerExample();
        example.createCriteria().andSellerIdEqualTo(sellerId);
        List<TbSeller> tbSellers = sellerMapper.selectByExample(example);
        TbSeller tbSeller = tbSellers.get(0);
        if(tbSeller == null){
            return null;
        }
        SellerVO vo = new SellerVO();
        BeanUtils.copyProperties(tbSeller,vo);
        return vo;
    }

    @Override
    public void updateStatus(SellerVO sellerVO) {
        TbSellerExample example = new TbSellerExample();
        example.createCriteria().andSellerIdEqualTo(sellerVO.getSellerId());
        List<TbSeller> tbSellers = sellerMapper.selectByExample(example);
        TbSeller tbSeller = tbSellers.get(0);
        if(sellerVO.getStatusId() == 1){
            tbSeller.setStatus("1");
            sellerMapper.updateByPrimaryKeySelective(tbSeller);
        }else if(sellerVO.getStatusId()  == 2){
            tbSeller.setStatus("2");
            sellerMapper.updateByPrimaryKeySelective(tbSeller);
        }else if(sellerVO.getStatusId()  == 3){
            tbSeller.setStatus("3");
            sellerMapper.updateByPrimaryKeySelective(tbSeller);
        }
    }
}
