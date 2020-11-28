package com.ssoggong.stonemanager_server.exception;

public class ChecklistNotFoundException extends RuntimeException {
    public ChecklistNotFoundException(Long checklistId){
        super("Invalid checklistId Exception : " + checklistId);
        System.out.println(getMessage());
    }
}
