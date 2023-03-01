package com.dongdongshop.filter;

import com.dongdongshop.data.Result;
import com.dongdongshop.dto.ItemDTO;
import com.dongdongshop.dto.SellerDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "dongdongshop-seller-service")//
public interface SllerServiceApi {

    @GetMapping("/seller/getSellerByName")
    Result<SellerDTO> getSellerByName(@RequestParam String username);

    @PostMapping("/goods/getItemById")
    Result<ItemDTO> getItemById(@RequestParam Long itemId);

    @PostMapping("/itemController/updateStockCount")
    Result updateStockCount(@RequestBody List<ItemDTO> itemVOList);
}
