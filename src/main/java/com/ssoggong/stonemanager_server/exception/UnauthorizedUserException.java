package com.ssoggong.stonemanager_server.exception;

public class UnauthorizedUserException extends UnauthorizedException {
    public UnauthorizedUserException(Long userId) {
        super("userId : " + userId.toString());
    }
}
