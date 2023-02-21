package com.dongdongshop.service.impl;

import com.alibaba.fastjson.JSON;
import com.dongdongshop.data.UserVo;
import com.dongdongshop.mapper.TbGoodsDescMapper;
import com.dongdongshop.mapper.TbGoodsMapper;
import com.dongdongshop.mapper.TbItemMapper;
import com.dongdongshop.model.TbGoods;
import com.dongdongshop.model.TbGoodsDesc;
import com.dongdongshop.model.TbGoodsExample;
import com.dongdongshop.model.TbItem;
import com.dongdongshop.service.GoodsService;
import com.dongdongshop.util.JwtUtil;
import com.dongdongshop.util.PageParms;
import com.dongdongshop.util.PageUtil;
import com.dongdongshop.vo.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Resource
    private TbGoodsMapper goodsMapper;

    @Resource
    private TbGoodsDescMapper descMapper;

    @Resource
    private TbItemMapper itemMapper;

    @Resource
    private JwtUtil jwtUtil;

    @Override
    @Transactional
    public void saveGoods(String zhuqifa,SaveVO saveVO) {
        Claims claims = jwtUtil.parseJwt(zhuqifa);
        String subject = claims.getSubject();
        UserVo userVo = JSON.parseObject(subject, UserVo.class);

        GoodsVO goodsVO = saveVO.getGoodsVO();
        goodsVO.setSellerId(userVo.getUserId());
        TbGoods tbGoods = new TbGoods();
        BeanUtils.copyProperties(goodsVO,tbGoods);
        goodsMapper.insertSelective(tbGoods);

        GoodsDescVO goodsDescVO = saveVO.getGoodsDescVO();
        goodsDescVO.setGoodsId(tbGoods.getId());
        TbGoodsDesc tbGoodsDesc = new TbGoodsDesc();
        BeanUtils.copyProperties(goodsDescVO,tbGoodsDesc);
        descMapper.insertSelective(tbGoodsDesc);

        List<ItemVO> voList = saveVO.getItemVOList();
        voList.stream().forEach(itemVO -> {
            TbItem tbItem = new TbItem();
            BeanUtils.copyProperties(itemVO,tbItem);
            tbItem.setCreateTime(new Date());
            tbItem.setUpdateTime(new Date());
            tbItem.setGoodsId(tbGoods.getId());
            tbItem.setSellerId(userVo.getUserId());
            tbItem.setSeller(userVo.getUsername());
            itemMapper.insertSelective(tbItem);
        });
    }

    @Override
    public PageUtil getGoodsList(PageParms<GoodsVO> pageParms) {
        GoodsVO parms = pageParms.getParms();
        TbGoodsExample example = new TbGoodsExample();
        TbGoodsExample.Criteria criteria = example.createCriteria();
        if(StringUtils.isNotBlank(parms.getGoodsName())){
            criteria.andGoodsNameLike("%" + parms.getGoodsName() + "%");
        }
        PageHelper.startPage(pageParms.getPageNum(),pageParms.getPageSize());
        List<TbGoods> tbGoods = goodsMapper.selectByExample(example);
        PageInfo<TbGoods> pageInfo = new PageInfo<>(tbGoods);
        List<GoodsVO> collect = pageInfo.getList().stream().map(g -> {
            GoodsVO goodsVO = new GoodsVO();
            BeanUtils.copyProperties(g, goodsVO);
            return goodsVO;
        }).collect(Collectors.toList());
        return new PageUtil<GoodsVO>(pageInfo.getPageNum(),pageInfo.getPageSize(),pageInfo.getTotal(),collect,pageInfo.getPages());
    }

    @Override
    public void deleteAll(Long[] ids) {
        TbGoodsExample example = new TbGoodsExample();
        example.createCriteria().andIdIn(Arrays.asList(ids));
        goodsMapper.deleteByExample(example);
    }

    @Override
    public void updateGoods(CommodityReviewVO vo) {
        Integer id = vo.getId();
        Long[] ids = vo.getIds();
        for (Long i : ids) {
            TbGoods tbGoods = goodsMapper.selectByPrimaryKey(i);
            if(id == 1){
                tbGoods.setAuditStatus("1");
                goodsMapper.updateByPrimaryKeySelective(tbGoods);
            }
            if (id == 2) {
                tbGoods.setAuditStatus("2");
                goodsMapper.updateByPrimaryKeySelective(tbGoods);
            }

        }
    }
}
