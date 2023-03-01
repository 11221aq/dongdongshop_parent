package com.dongdongshop.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
@Data
public class CartVO implements Serializable {

    private static final long serialVersionUID = 5685787696151880855L;
    private String sellerId;
    private List<CartdetailVO> cartdetailVOList;
}
