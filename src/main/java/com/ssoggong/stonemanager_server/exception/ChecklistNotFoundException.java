package com.ssoggong.stonemanager_server.exception;

public class ChecklistNotFoundException extends RuntimeException {
    public  ChecklistNotFoundException() { super("Invalid ChecklistId Exception"); }
}
