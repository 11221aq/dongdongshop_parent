package com.dongdongshop.service.impl;

import com.dongdongshop.mapper.TbSeckillGoodsMapper;
import com.dongdongshop.mapper.TbSeckillOrderMapper;
import com.dongdongshop.model.TbSeckillGoods;
import com.dongdongshop.model.TbSeckillGoodsExample;
import com.dongdongshop.model.TbSeckillOrder;
import com.dongdongshop.service.SeckillService;
import com.dongdongshop.util.IdWorker;
import com.dongdongshop.vo.SeckillGoodsVO;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeckillServiceImpl implements SeckillService {

    @Resource
    private TbSeckillGoodsMapper seckillGoodsMapper;

    @Resource
    private TbSeckillOrderMapper orderMapper;

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private IdWorker idWorker;

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

    @Override
    public SeckillGoodsVO getGoodsById(Long goodsId) {
        return (SeckillGoodsVO)redisTemplate.boundHashOps("seckillList").get(goodsId);
    }


    @Override
    public String submitSeckill(String userName,Long goodsId) {
        SeckillGoodsVO goodsVO = (SeckillGoodsVO) redisTemplate.boundHashOps("seckillList").get(goodsId);
        //判断商品是否为空
        if(goodsVO == null){
            throw new RuntimeException("商品不存在");
        }
        //先判断是否已经超过秒杀时间
        if(goodsVO.getEndTime().getTime() < System.currentTimeMillis()){
            throw new RuntimeException("秒杀已结束");
        }
        //判断是否还有库存
        if(goodsVO.getStockCount() <= 0){
            throw new RuntimeException("商品已售罄");
        }
        //扣减库存
        goodsVO.setStockCount(goodsVO.getStockCount() - 1);
        redisTemplate.boundHashOps("seckillList").put(goodsVO.getId(), goodsVO);
        //判断是否库存为0
        if(goodsVO.getStockCount() <= 0){
            //如果为0，删除redis中的数据 加入数据库
            TbSeckillGoods seckillGoods = new TbSeckillGoods();
            BeanUtils.copyProperties(goodsVO, seckillGoods);
            seckillGoodsMapper.updateByPrimaryKeySelective(seckillGoods);
        }
        //生成订单
        TbSeckillOrder order = new TbSeckillOrder();
        order.setCreateTime(new Date());
        long orderId = idWorker.nextId();
        order.setId(orderId);//id
        order.setMoney(goodsVO.getCostPrice());//售价
        order.setSeckillId(goodsVO.getId());//秒杀商品id
        order.setStatus("1");//未支付
        order.setUserId(userName);
        orderMapper.insertSelective(order);
        return String.valueOf(orderId);
    }

    @Override
    public void seckillJob() {
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
        System.out.println("预热成功" + new Date());
    }
}
