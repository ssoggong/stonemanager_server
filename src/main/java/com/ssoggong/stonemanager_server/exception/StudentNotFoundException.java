package com.ssoggong.stonemanager_server.exception;

public class StudentNotFoundException extends NotFoundException {
    public StudentNotFoundException(String studentId) {
        super("studentId : " + studentId);
    }
}
