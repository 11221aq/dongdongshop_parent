package com.dongdongshop.shiro;

import com.dongdongshop.data.Result;
import com.dongdongshop.dto.LoginUserDTO;
import com.dongdongshop.filter.LoginUserServiceApi;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

public class LoginRealm extends AuthorizingRealm {

    @Autowired
    private LoginUserServiceApi userServiceApi;


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        //根据名字去查询数据库
        Result<LoginUserDTO> result = userServiceApi.getUserByName(username);
        LoginUserDTO user = result.getData();
        if(user == null){
            return  null;
        }
        return new SimpleAuthenticationInfo(user,user.getPassword(), ByteSource.Util.bytes(user.getSalt()),getName());
    }
}
