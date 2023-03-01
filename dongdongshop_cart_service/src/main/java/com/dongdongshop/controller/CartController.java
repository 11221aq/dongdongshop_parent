package com.dongdongshop.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dongdongshop.data.Result;
import com.dongdongshop.data.UserVo;
import com.dongdongshop.em.ResultEnum;
import com.dongdongshop.service.CartService;
import com.dongdongshop.util.JwtUtil;
import com.dongdongshop.vo.CartVO;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cart")
public class CartController {

    @Autowired
    private JwtUtil jwtUtil;


    @Autowired
    private CartService cartService;

    @PostMapping("addCart")
    public Result setCart(@RequestHeader String zhuqifa , @RequestParam Long itemId,@RequestParam Integer num){
        try {
            Claims claims = jwtUtil.parseJwt(zhuqifa);
            String userId = claims.getId();
            cartService.addCart(userId, itemId, num);
            return Result.ok();
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e);
        }
    }

    @PostMapping("getCart")
    public Result getCart(@RequestHeader String zhuqifa){
        try {
            Claims claims = jwtUtil.parseJwt(zhuqifa);
            String userId = claims.getId();
            List<CartVO> list = cartService.getRedisCartVO(userId);
            return Result.ok().setData(list);
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e);
        }
    }

}
