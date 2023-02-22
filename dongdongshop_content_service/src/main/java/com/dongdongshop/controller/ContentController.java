package com.dongdongshop.controller;

import com.dongdongshop.data.Result;
import com.dongdongshop.em.ResultEnum;
import com.dongdongshop.service.ContentService;
import com.dongdongshop.vo.ContentCategoryVO;
import com.dongdongshop.vo.ContentStatusVO;
import com.dongdongshop.vo.ContentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("content")
public class ContentController {

    @Autowired
    private ContentService contentService;

    @PostMapping
    public Result getContentList(){
        try {
            List<ContentVO> list = contentService.getContentList();
            return Result.ok().setData(list);
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e);
        }
    }

    @PostMapping("addContent")
    public Result addContent(@RequestBody ContentVO contentVO){
        try {
            contentService.addContent(contentVO);
            return Result.ok();
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e);
        }
    }

    @GetMapping
    public Result getContentById(@RequestParam Long id){
        try {
            ContentVO vo = contentService.getContentById(id);
            return Result.ok().setData(vo);
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e);
        }
    }

    @PutMapping
    public Result updateContent(@RequestBody ContentVO contentVO){
        try {
            contentService.updateContent(contentVO);
            return Result.ok();
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e);
        }
    }

    @DeleteMapping
    public Result deleteAll(@RequestBody Long[] ids){
        try {
            contentService.deleteAll(ids);
            return Result.ok();
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e);
        }
    }

    @PostMapping("updateContent")
    public Result updateStatus(@RequestBody ContentStatusVO statusVO){
        try {
            contentService.updateStatus(statusVO);
            return Result.ok();
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e);
        }
    }

    @PostMapping("getContentImage")
    public Result getContentImage(@RequestParam Long categoryId){
        try {
            List<ContentVO> contentVOList =  contentService.getContentImage(categoryId);
            return Result.ok().setData(contentVOList);
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e);
        }
    }

}
