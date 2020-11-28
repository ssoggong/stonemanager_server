package com.ssoggong.stonemanager_server.exception;

public class TaskTagNotFoundException extends RuntimeException {
    public TaskTagNotFoundException(Long taskTagId){
        super("Invalid TaskId Exception : " + taskTagId);
        System.out.println(getMessage());
    }
}
