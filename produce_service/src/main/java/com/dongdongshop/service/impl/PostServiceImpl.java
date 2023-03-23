package com.dongdongshop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dongdongshop.model.Post;
import com.dongdongshop.service.PostService;
import com.dongdongshop.mapper.PostMapper;
import com.dongdongshop.vo.PostVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author 29555
* @description 针对表【post(岗位表)】的数据库操作Service实现
* @createDate 2023-03-21 21:39:36
*/
@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post>
    implements PostService{

    @Resource
    private PostMapper mapper;

    @Override
    public List<PostVO> getPostList(PostVO vo) {
        List<PostVO> collect = mapper.selectList(new LambdaQueryWrapper<Post>()
                .like(vo.getPcode() != null, Post::getPcode, vo.getPcode())
                .like(vo.getPname() != null, Post::getPname, vo.getPname())
                .eq(vo.getStatus() != null, Post::getStatus, vo.getStatus())
        ).stream().map(p -> {
            PostVO v = new PostVO();
            BeanUtils.copyProperties(p, v);
            return v;
        }).collect(Collectors.toList());
        return collect;
    }
}




