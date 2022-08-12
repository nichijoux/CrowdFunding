package com.zh.crowd.exception;

/**
 * 保存账号时账号已经存在
 */
public class AccountAlreadyExistException extends RuntimeException {
    private static final long serialVersion = 1L;

    public AccountAlreadyExistException() {
    }

    public AccountAlreadyExistException(String message) {
        super(message);
    }
}
