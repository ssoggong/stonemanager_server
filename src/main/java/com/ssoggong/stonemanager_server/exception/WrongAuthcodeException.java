package com.ssoggong.stonemanager_server.exception;

public class WrongAuthcodeException extends WrongValueException {
    public WrongAuthcodeException(String value) {
        super("Authcode : " + value);
    }
}
