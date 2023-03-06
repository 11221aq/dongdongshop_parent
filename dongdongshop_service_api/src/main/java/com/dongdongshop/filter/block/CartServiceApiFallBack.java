package com.dongdongshop.filter.block;

import com.dongdongshop.data.Result;
import com.dongdongshop.em.ResultEnum;
import com.dongdongshop.filter.CartServiceApi;
import org.springframework.stereotype.Component;

@Component
public class CartServiceApiFallBack implements CartServiceApi {
    @Override
    public Result getCart(String zhuqifa) {
        //此处编写降级业务
        return Result.result(ResultEnum.FALLBACK);
    }
}
