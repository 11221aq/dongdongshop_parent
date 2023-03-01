package com.dongdongshop.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class CartDTO implements Serializable {
    private String sellerId;
    private List<CartdetailDTO> cartdetailVOList;
}
