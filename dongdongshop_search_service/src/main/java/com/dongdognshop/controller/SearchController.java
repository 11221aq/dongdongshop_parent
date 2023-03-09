package com.dongdognshop.controller;

import com.dongdognshop.service.SearchService;
import com.dongdognshop.vo.ItemVO;
import com.dongdongshop.data.Result;
import com.dongdongshop.em.ResultEnum;
import com.dongdongshop.util.PageParms;
import com.dongdongshop.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("search")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @PostMapping
    public Result searchList(@RequestBody PageParms pageParms) {
        try {
            PageUtil<ItemVO> pageUtil = searchService.searchList(pageParms);
            return Result.ok().setData(pageUtil);
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e);
        }
    }

}
