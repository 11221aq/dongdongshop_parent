package com.dongdongshop.api;

import com.dongdongshop.data.Result;
import com.dongdongshop.dto.SellerDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(value = "dongdongshop-seller-service")//
public interface SllerServiceApi {
    @GetMapping("/seller/getSellerByName")
    Result<SellerDTO> getSellerByName(@RequestParam String username);
}
