package com.dongdongshop.vo;

import lombok.Data;

@Data
public class ContentVO {
    private Long id;

    private Long categoryId;

    private String categoryName;

    private String title;

    private String url;

    private String pic;

    private String status;

    private Integer sortOrder;


}