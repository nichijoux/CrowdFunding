package com.zh.crowd.exception;

/**
 * 登录失败的异常
 */
public class LoginFailedException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public LoginFailedException() {
    }

    public LoginFailedException(String message) {
        super(message);
    }
}
