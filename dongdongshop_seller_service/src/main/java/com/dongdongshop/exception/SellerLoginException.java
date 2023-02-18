package com.dongdongshop.exception;

import org.apache.shiro.authc.AuthenticationException;

public class SellerLoginException extends AuthenticationException {
    public SellerLoginException() {
    }

    public SellerLoginException(String message) {
        super(message);
    }
}
