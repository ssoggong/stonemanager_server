package com.ssoggong.stonemanager_server.exception;

public class WrongValueException extends RuntimeException{
    public WrongValueException(String message){
        super("Wrong Value exception : " + message);
        System.out.println(getMessage());
    }
}
