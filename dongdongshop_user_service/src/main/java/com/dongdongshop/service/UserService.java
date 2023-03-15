package com.dongdongshop.service;


import com.dongdongshop.model.TbUser;
import com.dongdongshop.vo.UserLonginVO;

public interface UserService {
    Integer loginUser(UserLonginVO userVo);

    TbUser getUserByName(String username);

    void updateUser(TbUser user);

    void retainCustomers();

    void weiboLogin(String username,String password);
}
