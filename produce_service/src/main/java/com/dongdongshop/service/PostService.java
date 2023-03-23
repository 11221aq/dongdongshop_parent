package com.dongdongshop.service;

import com.dongdongshop.model.Post;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dongdongshop.vo.PostVO;

import java.util.List;

/**
* @author 29555
* @description 针对表【post(岗位表)】的数据库操作Service
* @createDate 2023-03-21 21:39:36
*/
public interface PostService extends IService<Post> {

    List<PostVO> getPostList(PostVO vo);
}
