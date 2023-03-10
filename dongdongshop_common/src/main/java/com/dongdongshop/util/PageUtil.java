package com.dongdongshop.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageUtil<T> {
    private Integer pageNum;
    private Integer pageSize;
    private long total;
    private List<T> rows;
    private Integer pages;
    private Long page;
}
