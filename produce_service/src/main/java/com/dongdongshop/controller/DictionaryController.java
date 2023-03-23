package com.dongdongshop.controller;

import com.dongdongshop.data.Result;
import com.dongdongshop.em.ResultEnum;
import com.dongdongshop.model.Dictionary;
import com.dongdongshop.service.DictionaryService;
import com.dongdongshop.vo.DictionaryVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("dictionary")
public class DictionaryController {

    @Autowired
    private DictionaryService service;

    @PostMapping("getDictionary")
    public Result getDictionary(@RequestBody DictionaryVO vo, Date endTime) {
        try {
            List<DictionaryVO> voList = service.getDictionary(vo, endTime);
            return Result.ok().setData(voList);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }

    @PostMapping("addDictionary")
    public Result addDictionary(@RequestBody DictionaryVO vo) {
        try {
            Dictionary d = new Dictionary();
            BeanUtils.copyProperties(vo, d);
            service.save(d);
            return Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }

    @PostMapping("updateDictionary")
    public Result updateDictionary(@RequestBody DictionaryVO vo) {
        try {
            service.updateDictionary(vo);
            return Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }

    @PostMapping("getById")
    public Result getById(@RequestParam Long id) {
        try {
            Dictionary dictionary = service.getById(id);
            DictionaryVO vo = new DictionaryVO();
            BeanUtils.copyProperties(dictionary, vo);
            return Result.ok().setData(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }

    @PostMapping("deleteByid")
    public Result deleteByid(@RequestParam Long id) {
        try {
            service.deleteById(id);
            return Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }

}
