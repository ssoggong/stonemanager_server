package com.ssoggong.stonemanager_server.exception;

public class NotFoundException extends RuntimeException{
    public NotFoundException(){
        super("Invalid Value Exception : multiple value");
        System.out.println(getMessage());
    }
}
