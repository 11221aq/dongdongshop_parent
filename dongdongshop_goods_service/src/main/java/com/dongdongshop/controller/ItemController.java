package com.dongdongshop.controller;

import com.dongdongshop.data.Result;
import com.dongdongshop.em.ResultEnum;
import com.dongdongshop.service.ItemService;
import com.dongdongshop.util.PageParms;
import com.dongdongshop.util.PageUtil;
import com.dongdongshop.vo.TbItemCatVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping
    public Result getItemList(@RequestBody PageParms<TbItemCatVO> parms){
        try {
            PageUtil<TbItemCatVO> page = itemService.getItemList(parms);
            return Result.ok().setData(page);
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e);
        }
    }

    @PostMapping("getItem")
    public Result getItem(@RequestParam Long parentId){
        try {
            List<TbItemCatVO> list = itemService.getItem(parentId);
            return Result.ok().setData(list);
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e);
        }
    }

    @GetMapping
    private Result getItemNameById(@RequestParam Long id){
        try {
            Set<TbItemCatVO> set = itemService.getItemNameById(id);
            return Result.ok().setData(set);
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e);
        }
    }

    @PostMapping("addItem")
    public Result addItem(@RequestBody TbItemCatVO itemCatVO){
        try {
            itemService.addItem(itemCatVO);
            return Result.ok();
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e);
        }
    }

    @GetMapping("getItemById")
    public Result getItemById(@RequestParam Long id){
        try {
            TbItemCatVO vo = itemService.getItemById(id);
            return Result.ok().setData(vo);
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e);
        }
    }

    @PutMapping
    public Result updateItem(@RequestBody TbItemCatVO itemCatVO){
        try {
            itemService.updateItem(itemCatVO);
            return Result.ok();
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e);
        }
    }

    @DeleteMapping
    public Result deleteAll(@RequestBody Long[] ids){
        try {
            itemService.deleteAll(ids);
            return Result.ok();
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e);
        }
    }

}
