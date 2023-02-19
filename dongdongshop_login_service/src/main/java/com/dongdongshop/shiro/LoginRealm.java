package com.dongdongshop.shiro;

import com.dongdongshop.api.SllerServiceApi;
import com.dongdongshop.api.UserServiceApi;
import com.dongdongshop.data.Result;
import com.dongdongshop.dto.SellerDTO;
import com.dongdongshop.dto.UserDTO;
import com.dongdongshop.exception.SellerLoginException;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;

public class LoginRealm extends AuthorizingRealm {

    @Autowired
    private UserServiceApi userServiceApi;

    @Autowired
    private SllerServiceApi sllerServiceApi;

    @Autowired
    private HttpSession session;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("授权方法");
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("认证方法");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        Integer uid = (Integer)session.getAttribute("uid");
        if(uid == 1){
            //根据用户名去查询数据库是否有数据
            Result<UserDTO> result = userServiceApi.findUserByName(username);
            UserDTO user = result.getData();

            if (user == null) {
                return null;
            }
            return new SimpleAuthenticationInfo(user,user.getUpwd(), ByteSource.Util.bytes(user.getSalt()),user.getUname());
        }else{
            //先判断是否存在当前用户
            Result<SellerDTO> result = sllerServiceApi.getSellerByName(username);
            SellerDTO seller = result.getData();
            if(seller == null){
                return null;
            }

            if(!"1".equals(seller.getStatus())){
                throw  new SellerLoginException("账户待审核或审核未通过");
            }

            return new SimpleAuthenticationInfo(seller,seller.getPassword(), ByteSource.Util.bytes(seller.getLegalPersonCardId()),seller.getName());
        }

    }
}
