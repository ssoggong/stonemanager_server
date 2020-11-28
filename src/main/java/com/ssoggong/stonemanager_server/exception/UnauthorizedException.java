package com.ssoggong.stonemanager_server.exception;

public class UnauthorizedException extends RuntimeException{
    public UnauthorizedException(String message){
        super("Unauthorized Exception : " + message);
        System.out.println(getMessage());
    }
}
