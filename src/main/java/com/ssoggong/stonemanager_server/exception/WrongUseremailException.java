package com.ssoggong.stonemanager_server.exception;

public class WrongUseremailException extends WrongValueException {
    public WrongUseremailException(String userEmail) {
        super("userEmail : " + userEmail);
    }
}
