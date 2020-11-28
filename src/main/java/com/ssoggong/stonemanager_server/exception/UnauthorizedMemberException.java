package com.ssoggong.stonemanager_server.exception;

public class UnauthorizedMemberException extends UnauthorizedException {
    public UnauthorizedMemberException(Long userId){
        super("userId : " + userId.toString());
    }
}
