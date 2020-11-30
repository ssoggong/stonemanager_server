package com.ssoggong.stonemanager_server.exception;

public class WrongPasswordException extends WrongValueException {
    public WrongPasswordException(String password) {
        super("password : " + password);
    }
}
