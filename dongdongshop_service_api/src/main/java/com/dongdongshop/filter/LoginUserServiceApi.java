package com.dongdongshop.filter;

import com.dongdongshop.data.Result;
import com.dongdongshop.dto.AddressDTO;
import com.dongdongshop.dto.LoginUserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "dongdongshop-user-service")
public interface LoginUserServiceApi {
    @PostMapping("/user/getUserByName")
    Result<LoginUserDTO> getUserByName(@RequestParam String username);

    @PostMapping("/address/getAdressById")
    Result<AddressDTO> getAdressById(@RequestParam Long addressId);
}
