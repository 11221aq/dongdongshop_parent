package com.dongdongshop.controller;

import com.alibaba.fastjson.JSONObject;
import com.dongdongshop.data.Result;
import com.dongdongshop.data.UserVo;
import com.dongdongshop.em.ResultEnum;
import com.dongdongshop.model.TbUser;
import com.dongdongshop.service.AddressService;
import com.dongdongshop.util.JwtUtil;
import com.dongdongshop.vo.AddressVO;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("address")
public class AddressController {
    
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AddressService addressService;

    @PostMapping("getAddress")
    public Result getAddress(@RequestHeader String zhuqifa){
        try {
            Claims claims = jwtUtil.parseJwt(zhuqifa);
            UserVo userVo = JSONObject.parseObject(claims.getSubject(), UserVo.class);
            List<AddressVO> addressVOList  = addressService.getAddress(userVo.getUsername());
            return Result.ok().setData(addressVOList);
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e);
        }
    }

    @PostMapping("getAdressById")
    public Result getAdressById(@RequestParam Long addressId){
        try {
            AddressVO addressVO = addressService.getAdressById(addressId);
            return Result.ok().setData(addressVO);
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e);
        }
    }

}
