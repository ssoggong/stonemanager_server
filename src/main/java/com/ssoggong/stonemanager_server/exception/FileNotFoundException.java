package com.ssoggong.stonemanager_server.exception;

public class FileNotFoundException extends RuntimeException {
    public FileNotFoundException(Long fileId){
        super("Invalid fileId Exception : " + fileId);
        System.out.println(getMessage());
    }
}
