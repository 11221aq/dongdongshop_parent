package com.dongdongshop.controller;

import com.dongdongshop.data.Result;
import com.dongdongshop.em.ResultEnum;
import com.dongdongshop.model.Post;
import com.dongdongshop.service.PostService;
import com.dongdongshop.vo.PostVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("post")
public class PostController {


    @Autowired
    private PostService service;

    @PostMapping("getPostList")
    public Result getPostList(@RequestBody PostVO vo){
        try {
            List<PostVO> voList = service.getPostList(vo);
           return Result.ok().setData(voList);
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }

    @PostMapping("addPost")
    public Result addPost(@RequestBody PostVO vo){
        try {
            Post p = new Post();
            BeanUtils.copyProperties(vo,p);
            p.setCreatTime(new Date());
            service.save(p);
            return Result.ok();
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }

    @PostMapping("updatePost")
    public Result updatePost(@RequestBody PostVO vo){
        try {
            Post p = new Post();
            BeanUtils.copyProperties(vo,p);
            service.updateById(p);
            return Result.ok();
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }

    @PostMapping("getPostById")
    public Result getPostById(@RequestParam Integer id){
        try {
            Post byId = service.getById(id);
            PostVO v = new PostVO();
            BeanUtils.copyProperties(byId,v);
            return Result.ok().setData(v);
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }

    @PostMapping("deleteById")
    public Result deleteById(@RequestParam Integer id){
        try {
            service.removeById(id);
            return Result.ok();
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }

}
