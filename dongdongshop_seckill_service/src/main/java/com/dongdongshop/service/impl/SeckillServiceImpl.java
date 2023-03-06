package com.dongdongshop.service.impl;

import com.dongdongshop.mapper.TbSeckillGoodsMapper;
import com.dongdongshop.model.TbSeckillGoods;
import com.dongdongshop.model.TbSeckillGoodsExample;
import com.dongdongshop.service.SeckillService;
import com.dongdongshop.vo.SeckillGoodsVO;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeckillServiceImpl implements SeckillService {

    @Resource
    private TbSeckillGoodsMapper seckillGoodsMapper;

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public void cachePreHot() {
        Date now = new Date();
        TbSeckillGoodsExample example = new TbSeckillGoodsExample();
        TbSeckillGoodsExample.Criteria criteria = example.createCriteria();
        criteria.andStartTimeLessThanOrEqualTo(now);
        criteria.andEndTimeGreaterThanOrEqualTo(now);
        List<TbSeckillGoods> tbSeckillGoods = seckillGoodsMapper.selectByExample(example);
        tbSeckillGoods.stream().map(g -> {
            SeckillGoodsVO vo = new SeckillGoodsVO();
            BeanUtils.copyProperties(g, vo);
            return vo;
        }).forEach(g -> {
            redisTemplate.boundHashOps("seckillList").put(g.getId(), g);
        });

    }

    @Override
    public List<SeckillGoodsVO> getSeckillList() {
        return (List<SeckillGoodsVO>)redisTemplate.boundHashOps("seckillList").values();
    }
}
