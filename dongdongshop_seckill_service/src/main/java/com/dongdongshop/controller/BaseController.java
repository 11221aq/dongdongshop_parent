package com.dongdongshop.controller;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.dongdongshop.data.Result;

public class BaseController {
    public Result hotBlockHandler(BlockException e) {
        return Result.fallback();
    }
}
