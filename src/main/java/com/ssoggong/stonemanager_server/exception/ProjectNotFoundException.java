package com.ssoggong.stonemanager_server.exception;

public class ProjectNotFoundException extends RuntimeException {
    public ProjectNotFoundException(Long projectId) {
        super("Invalid ProjectId Exception : " + projectId);
        System.out.println(getMessage());
    }
}
