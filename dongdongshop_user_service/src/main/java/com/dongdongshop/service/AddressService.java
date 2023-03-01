package com.dongdongshop.service;

import com.dongdongshop.vo.AddressVO;

import java.util.List;

public interface AddressService {
    List<AddressVO> getAddress(String username);

    AddressVO getAdressById(Long addressId);
}
