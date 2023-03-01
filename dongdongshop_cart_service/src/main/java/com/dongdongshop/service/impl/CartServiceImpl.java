package com.dongdongshop.service.impl;

import com.dongdongshop.data.Result;
import com.dongdongshop.dto.ItemDTO;
import com.dongdongshop.filter.SllerServiceApi;
import com.dongdongshop.service.CartService;
import com.dongdongshop.vo.CartVO;
import com.dongdongshop.vo.CartdetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CartServiceImpl implements CartService {

    @Resource
    private RedisTemplate redisTemplate;

    @Autowired
    private SllerServiceApi sllerServiceApi;

    @Override
    public void addCart(String userId, Long itemId, Integer num) {
        //1.首先从redis中获取 登录用户的购物车数据
        List<CartVO> redisCartVOList = getRedisCartVO(userId);
        //2.根据商品的id 去查询商品的数据
        Result<ItemDTO> result = sllerServiceApi.getItemById(itemId);
        ItemDTO itemDTO = result.getData();
        //根据商品的数据获取商家的id
        String sellerId = itemDTO.getSellerId();
        //4.判断redis中有没有该商家的id
        CartVO cart = null;
        for (CartVO cartVO : redisCartVOList) {
            if(Objects.equals(cartVO.getSellerId(), sellerId)){
                cart = cartVO;
                break;
            }
        }
        //没有,则说明之前没有加过这个商家的商品,
        //我们需要创建购物车对象 加入list中并且再存到缓存中
        if(cart == null){
            cart = new CartVO();
            cart.setSellerId(sellerId);
            //创建明细列表
            List<CartdetailVO> cartdetailVOList = new ArrayList<>();
            //创建明细对象
            CartdetailVO cartdetailVO = new CartdetailVO();
            cartdetailVO.setGoodsId(itemDTO.getGoodsId());
            cartdetailVO.setItemId(itemDTO.getId());
            cartdetailVO.setNum(num);
            cartdetailVO.setTitle(itemDTO.getTitle());
            cartdetailVO.setPicPath(itemDTO.getImage());
            cartdetailVO.setPrice(itemDTO.getPrice());
            cartdetailVO.setTotalFee(new BigDecimal(cartdetailVO.getPrice().doubleValue() * num));
            cartdetailVOList.add(cartdetailVO);
            cart.setCartdetailVOList(cartdetailVOList);
            //将新添加的购物车放到老的购物车中
            redisCartVOList.add(cart);
        }else{//如果有 说明购物车里面有这个商家商品
            //判断是否存在该商品
            List<CartdetailVO> cartdetailVOList = cart.getCartdetailVOList();
            CartdetailVO cartdetail = null;
            for (CartdetailVO cartdetailVO : cartdetailVOList) {
                if(Objects.equals(cartdetailVO.getItemId(),itemDTO.getId())){
                    cartdetail = cartdetailVO;
                    break;
                }
            }
            if(cartdetail == null){//若不存在 新建追加进去
                cartdetail = new CartdetailVO();
                cartdetail.setGoodsId(itemDTO.getGoodsId());
                cartdetail.setItemId(itemDTO.getId());
                cartdetail.setNum(num);
                cartdetail.setTitle(itemDTO.getTitle());
                cartdetail.setPicPath(itemDTO.getImage());
                cartdetail.setPrice(itemDTO.getPrice());
                cartdetail.setTotalFee(new BigDecimal(cartdetail.getPrice().doubleValue() * num));
                cartdetailVOList.add(cartdetail);
            }else{ //存在 则 数量加一 价格 = 数量 * 单价
                cartdetail.setNum(cartdetail.getNum() + num);
                cartdetail.setTotalFee(new BigDecimal(cartdetail.getNum() * cartdetail.getPrice().doubleValue()));
                if(cartdetail.getNum() <= 0){//即在购物车中删除对应商品
                    cartdetailVOList.remove(cartdetail);
                }
                //如果明细列表删完 即 集合大小为0l 那么在 大集合中也应该删除
                if(cartdetailVOList.size() == 0){
                    redisCartVOList.remove(cart);
                }
            }

        }
        setRedisCartVO(userId,redisCartVOList);
    }


    public List<CartVO> getRedisCartVO(String userId){
        List<CartVO> cartList = (List<CartVO>)redisTemplate.boundHashOps("cartList").get(userId);
        if(cartList == null){
            cartList = new ArrayList<>();
        }
        return  cartList;
    }

    private void setRedisCartVO(String userId, List<CartVO> list){
        redisTemplate.boundHashOps("cartList").put(userId,list);
    }

}
