package com.ssoggong.stonemanager_server.exception;

public class ProjectNotFoundException extends NotFoundException {
    public ProjectNotFoundException(Long projectId) {
        super("projectId : " + projectId.toString());
    }
}
