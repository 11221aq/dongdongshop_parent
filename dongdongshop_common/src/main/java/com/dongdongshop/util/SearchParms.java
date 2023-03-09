package com.dongdongshop.util;

import lombok.Data;

@Data
public class SearchParms<T> {
    private Integer pageNum;
    private Integer pageSize;
    private T parms;
}
