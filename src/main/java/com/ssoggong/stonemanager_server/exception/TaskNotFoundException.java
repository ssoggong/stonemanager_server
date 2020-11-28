package com.ssoggong.stonemanager_server.exception;

public class TaskNotFoundException extends NotFoundException {
    public TaskNotFoundException(Long taskId){
        super("taskId : " + taskId.toString());
    }
}
