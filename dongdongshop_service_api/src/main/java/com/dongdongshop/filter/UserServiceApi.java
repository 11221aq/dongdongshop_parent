package com.dongdongshop.filter;

import com.dongdongshop.data.Result;
import com.dongdongshop.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "dongdongshop-goods-service")
public interface UserServiceApi {

    @GetMapping("/user")
    Result<UserDTO> findUserByName(@RequestParam String name);
}