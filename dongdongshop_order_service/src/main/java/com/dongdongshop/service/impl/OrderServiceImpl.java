package com.dongdongshop.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.dongdongshop.data.Result;
import com.dongdongshop.data.UserVo;
import com.dongdongshop.dto.AddressDTO;
import com.dongdongshop.dto.CartDTO;
import com.dongdongshop.dto.CartdetailDTO;
import com.dongdongshop.dto.ItemDTO;
import com.dongdongshop.filter.CartServiceApi;
import com.dongdongshop.filter.LoginUserServiceApi;
import com.dongdongshop.filter.SllerServiceApi;
import com.dongdongshop.filter.TradeServiceApi;
import com.dongdongshop.mapper.TbOrderItemMapper;
import com.dongdongshop.mapper.TbOrderMapper;
import com.dongdongshop.model.TbOrder;
import com.dongdongshop.model.TbOrderExample;
import com.dongdongshop.model.TbOrderItem;
import com.dongdongshop.model.TbOrderItemExample;
import com.dongdongshop.service.OrderService;
import com.dongdongshop.util.IdWorker;
import com.dongdongshop.util.JwtUtil;
import com.dongdongshop.vo.AlipayTradeVO;
import com.dongdongshop.vo.OrderPageVO;
import com.dongdongshop.vo.OrderVO;
import io.jsonwebtoken.Claims;
import io.seata.spring.annotation.GlobalTransactional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private TbOrderMapper orderMapper;

    @Resource
    private TbOrderItemMapper orderItemMapper;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CartServiceApi cartServiceApi;

    @Autowired
    private LoginUserServiceApi userServiceApi;

    @Autowired
    private SllerServiceApi sllerServiceApi;

    @Autowired
    private TradeServiceApi tradeServiceApi;

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    @Transactional//本地事务注解
    @GlobalTransactional(rollbackFor = Exception.class)//添加全局事务注解
    public AlipayTradeVO submitOrder(String zhuqifa, Long addressId, String payType) {
        //根据用户的信息 从购物车系统获取购物车数据
        Result<List<CartDTO>> result = cartServiceApi.getCart(zhuqifa);
        if(result.getCode() == 10001){
            throw new RuntimeException("购物车为空");
        }
        List<CartDTO> cartDTOList = result.getData();
        //获取当前登录的用户
        Claims claims = jwtUtil.parseJwt(zhuqifa);
        UserVo userVo = JSONObject.parseObject(claims.getSubject(), UserVo.class);
        //查询当前用户选择的收获地址
        Result<AddressDTO> adressById = userServiceApi.getAdressById(addressId);
        AddressDTO adress = adressById.getData();

        //创建 订单集合
        List<TbOrder> orderList = new ArrayList<>();

        //创建订单明细集合
        List<TbOrderItem> orderItemList = new ArrayList<>();

        //定义支付使用的订单号
        long out_trade_no = idWorker.nextId();

        //定义支付时的金额
        BigDecimal allMon = new BigDecimal(0);

        //定义标题
        StringBuilder builder = new StringBuilder();

        //扣减库存的商品集合
        List<ItemDTO> itemDTOList = new ArrayList<>();

        for (CartDTO cartDTO : cartDTOList) {//循环用户要提交订单的商品
            //使用 idworker 生成订单编号
            long orderId = idWorker.nextId();//生成订单id
            //创建订单对象
            TbOrder order = new TbOrder();
            order.setOrderId(orderId);//订单id
            order.setPaymentType(payType);//支付方式
            order.setStatus("1");//订单状态
            order.setCreateTime(new Date());//创建时间
            order.setUpdateTime(new Date());//更新时间
            order.setUserId(userVo.getUsername());//用户id
            order.setReceiverAreaName(adress.getAddress());//收货地址
            order.setReceiverMobile(adress.getMobile());//收货人手机号
            order.setReceiver(adress.getContact());//收货人
            order.setSellerId(cartDTO.getSellerId());//商家id
            order.setUserId(userVo.getUsername());//用户id
            order.setOrderNum(String.valueOf(out_trade_no));
            BigDecimal sum= new BigDecimal(0);
            //循环明细
            for (CartdetailDTO orderMapper : cartDTO.getCartdetailVOList()) {
                //获取商品明细里的 总价格 累加
                sum = sum.add(orderMapper.getTotalFee());
                allMon = allMon.add(orderMapper.getTotalFee());
                //创建订单明细对象
                TbOrderItem item = new TbOrderItem();
                item.setGoodsId(orderMapper.getGoodsId());//商品id
                item.setItemId(orderMapper.getItemId());//商品id
                item.setId(idWorker.nextId());//订单明细id  根据 idworker生成id
                item.setNum(orderMapper.getNum());//数量
                item.setOrderId(orderId);//订单id
                item.setPicPath(orderMapper.getPicPath());//商品图片
                item.setPrice(orderMapper.getPrice());//商品价格
                item.setSellerId(cartDTO.getSellerId());//商家id
                item.setTotalFee(orderMapper.getTotalFee());//总价
                item.setTitle(orderMapper.getTitle());//商品名称
                builder.append(orderMapper.getTitle());
                item.setOrderNum(String.valueOf(out_trade_no));
                item.setStatus("1");
                orderItemList.add(item);

                //扣减库存
                ItemDTO itemDTO = new ItemDTO();
                itemDTO.setId(orderMapper.getItemId());
                itemDTO.setUpdateNum(orderMapper.getNum());//更新数量
                itemDTOList.add(itemDTO);
            }
            order.setPayment(sum);//实付款
            orderList.add(order);
        }

        //扣减库存
        sllerServiceApi.updateStockCount(itemDTOList);
        //保存主表数据
        orderMapper.insertBath(orderList);

        //保存明细表数据
        orderItemMapper.insertBath(orderItemList);

        redisTemplate.boundHashOps("cartList").delete(userVo.getUserId());

        AlipayTradeVO vo = new AlipayTradeVO();
        vo.setOut_trade_no(String.valueOf(out_trade_no));
        vo.setTotal_amount(String.valueOf(allMon));
        vo.setSubject(builder.toString());
        return vo;
    }


    @Override
    @Transactional
    public void updateOrderStatus(String out_trade_no, String trade_no) {
        TbOrderExample example = new TbOrderExample();
        example.createCriteria().andOrderNumEqualTo(out_trade_no);
        List<TbOrder> orderList = orderMapper.selectByExample(example);
        orderList.stream().forEach(o -> {
            o.setStatus("2");
            o.setTradeNo(trade_no);
            o.setPaymentTime(new Date());
            o.setUpdateTime(new Date());
            orderMapper.updateByPrimaryKeySelective(o);
        });

        TbOrderItemExample itemExample = new TbOrderItemExample();
        itemExample.createCriteria().andOrderNumEqualTo(out_trade_no);
        List<TbOrderItem> tbOrderItems = orderItemMapper.selectByExample(itemExample);
        tbOrderItems.stream().forEach(i -> {
            i.setStatus("2");
            i.setTradeNo(trade_no);
            orderItemMapper.updateByPrimaryKeySelective(i);
        });

    }


    @Override
    public List<OrderVO> getOrderByUserId(String zhuqifa,String status) {
        //获取当前登录的用户
        Claims claims = jwtUtil.parseJwt(zhuqifa);
        UserVo userVo = JSONObject.parseObject(claims.getSubject(), UserVo.class);
        //根据当前用户 名字 查询对应 订单
        TbOrderExample e = new TbOrderExample();
        TbOrderExample.Criteria criteria = e.createCriteria();
        criteria.andUserIdEqualTo(userVo.getUsername());
        if(StringUtils.isNotBlank(status)){
            criteria.andStatusEqualTo(status);
        }
        List<OrderVO> voList = new ArrayList<>();

        List<TbOrderItem> tbOrderItems = orderItemMapper.selectByExample(null);
        Map<Long, List<TbOrderItem>> collect = tbOrderItems.stream().collect(Collectors.groupingBy(TbOrderItem::getOrderId));
        orderMapper.selectByExample(e).stream().forEach(o -> {
            OrderVO vo = new OrderVO(); 
            BeanUtils.copyProperties(o, vo);
            List<OrderPageVO> orderPageVOList = collect.get(o.getOrderId()).stream().map(item -> {
                OrderPageVO pageVO = new OrderPageVO();
                BeanUtils.copyProperties(item, pageVO);
                return pageVO;
            }).collect(Collectors.toList());
            vo.setOrderPageVOList(orderPageVOList);
            voList.add(vo);
        });
        return voList;
    }


    @Override
    @Transactional
    public void getOrderById(Long id) {
        TbOrderItem item = orderItemMapper.selectByPrimaryKey(id);
        Result result = tradeServiceApi.tradeRefund(item.getTradeNo(), item.getTotalFee().toString(), item.getOrderNum());
        if(Objects.equals(result.getCode(),10008)){
            item.setStatus("6");//退款成功
            orderItemMapper.updateByPrimaryKeySelective(item);
        }
        TbOrderItemExample example = new TbOrderItemExample();
        example.createCriteria().andOrderIdEqualTo(item.getOrderId());
        List<TbOrderItem> tbOrderItems = orderItemMapper.selectByExample(example);
        boolean flag = false;
        for (TbOrderItem tbOrderItem : tbOrderItems) {
            if(!Objects.equals(tbOrderItem.getStatus(),"6")){
                flag=true;
                break;
            }
        }
        if(!flag){
            TbOrder order = orderMapper.selectByPrimaryKey(item.getOrderId());
            order.setStatus("6");
            orderMapper.updateByPrimaryKeySelective(order);
        }

    }
}
