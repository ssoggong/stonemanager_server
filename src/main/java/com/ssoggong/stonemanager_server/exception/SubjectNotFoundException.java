package com.ssoggong.stonemanager_server.exception;

public class SubjectNotFoundException extends NotFoundException{
    public SubjectNotFoundException(Long subjectId) {
        super("subjectId : " + subjectId.toString());
    }
}
