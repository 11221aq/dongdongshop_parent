package com.dongdongshop.filter;

import com.dongdongshop.data.Result;
import com.dongdongshop.dto.CartDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(value = "dongdongshop-cart-service")
public interface CartServiceApi {
    @PostMapping("/cart/getCart")
    Result<List<CartDTO>> getCart(@RequestHeader String zhuqifa);
}
