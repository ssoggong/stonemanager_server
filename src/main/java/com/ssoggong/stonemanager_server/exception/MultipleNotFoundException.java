package com.ssoggong.stonemanager_server.exception;

public class MultipleNotFoundException extends NotFoundException {
    public MultipleNotFoundException(){
        super("multiple value");
    }
}
