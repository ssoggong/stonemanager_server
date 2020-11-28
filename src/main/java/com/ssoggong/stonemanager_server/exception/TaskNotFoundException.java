package com.ssoggong.stonemanager_server.exception;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(Long taskId){
        super("Invalid TaskId Exception : " +taskId);
        System.out.println(getMessage());
    }
    public TaskNotFoundException() { super("Invalid TaskId Exception"); }
}
