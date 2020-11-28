package com.ssoggong.stonemanager_server.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(Long userId)
    {
        super("Invalid UserId Exception : " +userId);
        System.out.println(getMessage());
    }
}
