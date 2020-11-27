package com.ssoggong.stonemanager_server.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(){
        super("Invalid UserId Exception");
    }
}
