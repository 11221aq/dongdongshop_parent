package com.dongdongshop.token;

import com.dongdongshop.em.LoginEnum;
import lombok.Data;
import org.apache.shiro.authc.UsernamePasswordToken;
@Data
public class LoginToken extends UsernamePasswordToken {
    private LoginEnum loginEnum;

    public LoginToken(String username, String password, LoginEnum loginEnum) {
        super(username, password);
        this.loginEnum = loginEnum;
    }
}
