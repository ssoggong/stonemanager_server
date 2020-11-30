package com.ssoggong.stonemanager_server.exception;

public class WrongUsernameException extends WrongValueException{
    public WrongUsernameException(String userName) {
        super("username : " + userName);
    }
}
