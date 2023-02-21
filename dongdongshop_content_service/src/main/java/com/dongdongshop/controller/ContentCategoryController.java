package com.dongdongshop.controller;

import com.dongdongshop.data.Result;
import com.dongdongshop.em.ResultEnum;
import com.dongdongshop.service.ContentCateGoryService;
import com.dongdongshop.vo.ContentCategoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("contentCategory")
public class ContentCategoryController {

    @Autowired
    private ContentCateGoryService contentService;

    @PostMapping
    public Result getContentList(@RequestParam String name){
        try {
            List<ContentCategoryVO> list = contentService.getContentList(name);
            return Result.ok().setData(list);
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e);
        }
    }

    @PostMapping("addContent")
    public Result addContent(@RequestBody ContentCategoryVO vo){
        try {
            contentService.addContent(vo);
            return Result.ok();
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e);
        }
    }

    @GetMapping
    public Result getContent(@RequestParam Long id){
        try {
            ContentCategoryVO vo = contentService.getContent(id);
            return Result.ok().setData(vo);
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e);
        }
    }

    @PutMapping
    public Result putContent(@RequestBody ContentCategoryVO vo){
        try {
            contentService.putContent(vo);
            return Result.ok();
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e);
        }
    }

    @DeleteMapping
    public Result deleteAll(@RequestBody Long[] ids) {
        try {
            contentService.deleteAll(ids);
            return Result.ok();
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e);
        }
    }

}
