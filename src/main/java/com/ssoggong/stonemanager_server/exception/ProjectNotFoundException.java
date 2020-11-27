package com.ssoggong.stonemanager_server.exception;

public class ProjectNotFoundException extends RuntimeException {
    public ProjectNotFoundException() { super("Invalid ProjectId Exception"); }
}
