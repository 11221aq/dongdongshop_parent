package com.dongdongshop.shiro;

import com.dongdongshop.exception.SellerLoginException;
import com.dongdongshop.model.TbSeller;
import com.dongdongshop.service.SellerService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private SellerService sellerService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("进入认证方法");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();

        //先判断是否存在当前用户
        TbSeller seller = sellerService.getSellerByName(username);
        if(seller == null){
            return null;
        }

        if(!"1".equals(seller.getStatus())){
            throw  new SellerLoginException("账户待审核或审核未通过");
        }

        return new SimpleAuthenticationInfo(seller,seller.getPassword(), ByteSource.Util.bytes(seller.getLegalPersonCardId()),seller.getName());
    }

    public static void main(String[] args) {
        String str1 ="1";
        String str2 = "2";
        System.out.println("1".equals(str1));
    }
}
