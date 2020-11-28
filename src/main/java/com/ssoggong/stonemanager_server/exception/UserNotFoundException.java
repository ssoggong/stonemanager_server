package com.ssoggong.stonemanager_server.exception;

public class UserNotFoundException extends NotFoundException{
    public UserNotFoundException(Long userId) {
        super("userId : " + userId.toString());
    }
}
