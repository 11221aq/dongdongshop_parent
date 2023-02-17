package com.dongdongshop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TbItemCat {
    private Long id;

    private Long parentId;

    private String name;

    private Long typeId;

}