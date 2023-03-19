package com.dongdongshop.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class TypeVO implements Serializable {
    private Integer tid;

    private String tname;

    private Integer pid;

    private List<TypeVO> voList;
}