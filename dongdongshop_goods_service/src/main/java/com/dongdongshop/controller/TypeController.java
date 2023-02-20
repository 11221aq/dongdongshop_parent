package com.dongdongshop.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dongdongshop.data.Result;
import com.dongdongshop.em.ResultEnum;
import com.dongdongshop.model.TbTypeTemplate;
import com.dongdongshop.service.SpecService;
import com.dongdongshop.service.TypeService;
import com.dongdongshop.vo.SpecVO;
import com.dongdongshop.vo.TbSpecificationVO;
import com.dongdongshop.vo.TbTypeTemplateVO;
import com.dongdongshop.vo.TemplateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("type")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private SpecService specService;

    @PostMapping
    public Result geTypeList(@RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10")Integer pageSize,@RequestBody TbTypeTemplateVO typeVO){
        try {
            Map<String ,Object> map = typeService.geTypeList(pageNum,pageSize,typeVO.getName());
            return Result.result(ResultEnum.SUCCESS).setData(map);
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e);
        }
    }

    @PostMapping("save")
    public Result save(@RequestBody TbTypeTemplateVO typeTemplateVO){
        try {
            typeService.save(typeTemplateVO);
            return Result.result(ResultEnum.SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e);
        }
    }

    //根据模板的id查询模板的对象
    @GetMapping
    public Result getTypeById(@RequestParam Long id){
        try {
            TbTypeTemplateVO typeById = typeService.getTypeById(id);

            TemplateVO templateVO = new TemplateVO();

            templateVO.setTypeTemplateVO(typeById);

            List<Map> mapList = JSONObject.parseArray(typeById.getSpecIds(), Map.class);
            List<TbSpecificationVO> collect = mapList.stream().map(map -> {
                TbSpecificationVO specificationVO = new TbSpecificationVO();
                specificationVO.setId(((Integer)map.get("id")).longValue());
                specificationVO.setSpecName((String) map.get("text"));
                return specificationVO;
            }).collect(Collectors.toList());

            List<SpecVO> specVOList = collect.stream().map(c -> specService
                    .updateTrim(c.getId())).collect(Collectors.toList());

            templateVO.setSpecVOList(specVOList);
            return Result.result(ResultEnum.SUCCESS).setData(templateVO);
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e);
        }
    }

    @PutMapping
    public Result updateType(@RequestBody TbTypeTemplateVO typeTemplateVO){
        try {
            typeService.updateType(typeTemplateVO);
            return Result.result(ResultEnum.SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e);
        }
    }

    @DeleteMapping
    public Result deleteAll(@RequestBody Long[] ids){
        try {
            typeService.deleteAll(ids);
            return Result.result(ResultEnum.SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e);
        }
    }


}
