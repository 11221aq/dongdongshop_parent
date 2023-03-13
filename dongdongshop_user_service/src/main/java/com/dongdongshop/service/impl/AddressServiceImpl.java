package com.dongdongshop.service.impl;

import com.dongdongshop.mapper.TbAddressMapper;
import com.dongdongshop.model.TbAddress;
import com.dongdongshop.model.TbAddressExample;
import com.dongdongshop.service.AddressService;
import com.dongdongshop.vo.AddressVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService {

    @Resource
    private TbAddressMapper addressMapper;

    @Override
    public List<AddressVO> getAddress(String username) {
        TbAddressExample example = new TbAddressExample();
        example.createCriteria().andUserIdEqualTo(username);
        List<TbAddress> tbAddresses = addressMapper.selectByExample(example);
        List<AddressVO> collect = tbAddresses.stream().map(a -> {
            AddressVO addressVO = new AddressVO();
            BeanUtils.copyProperties(a, addressVO);
            return addressVO;
        }).collect(Collectors.toList());
        return collect;
    }

    @Override
    public AddressVO getAdressById(Long addressId) {
        TbAddress tbAddress = addressMapper.selectByPrimaryKey(addressId);
        AddressVO addressVO = new AddressVO();
        BeanUtils.copyProperties(tbAddress, addressVO);
        return addressVO;
    }
}
