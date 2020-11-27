package com.ssoggong.stonemanager_server.exception;

public class UserNotFountException extends RuntimeException{
    public UserNotFountException(Long userId){
        super("not found user by id: " + userId);
    }
}
