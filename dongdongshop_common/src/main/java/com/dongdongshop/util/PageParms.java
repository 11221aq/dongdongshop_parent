package com.dongdongshop.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageParms<T> {
    private Integer pageNum;
    private Integer pageSize;
    private String title;
}
