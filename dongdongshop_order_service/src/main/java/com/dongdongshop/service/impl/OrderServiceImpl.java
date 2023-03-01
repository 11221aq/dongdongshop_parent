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
import com.dongdongshop.filter.UserServiceApi;
import com.dongdongshop.mapper.TbOrderItemMapper;
import com.dongdongshop.mapper.TbOrderMapper;
import com.dongdongshop.model.TbOrder;
import com.dongdongshop.model.TbOrderItem;
import com.dongdongshop.service.OrderService;
import com.dongdongshop.util.IdWorker;
import com.dongdongshop.util.JwtUtil;
import io.jsonwebtoken.Claims;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @Override
    @Transactional//本地事务注解
    @GlobalTransactional(rollbackFor = Exception.class)//添加全局事务注解
    public void submitOrder(String zhuqifa, Long addressId, String payType) {
        //根据用户的信息 从购物车系统获取购物车数据
        Result<List<CartDTO>> result = cartServiceApi.getCart(zhuqifa);
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

        //扣减库存
        List<ItemDTO> itemDTOList = new ArrayList<>();

        for (CartDTO cartDTO : cartDTOList) {
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

            BigDecimal sum= new BigDecimal(0);
            //循环明细
            for (CartdetailDTO orderMapper : cartDTO.getCartdetailVOList()) {
                sum = sum.add(orderMapper.getTotalFee());
                TbOrderItem item = new TbOrderItem();
                item.setGoodsId(orderMapper.getGoodsId());//商品id
                item.setItemId(orderMapper.getItemId());//商品id
                item.setId(idWorker.nextId());//订单明细id
                item.setNum(orderMapper.getNum());//数量
                item.setOrderId(orderId);//订单id
                item.setPicPath(orderMapper.getPicPath());//商品图片
                item.setPrice(orderMapper.getPrice());//商品价格
                item.setSellerId(cartDTO.getSellerId());//商家id
                item.setTotalFee(orderMapper.getTotalFee());//总价
                item.setTitle(orderMapper.getTitle());//商品名称
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
 //       int x = 9/0;
        //保存明细表数据
        orderItemMapper.insertBath(orderItemList);
    }
}
