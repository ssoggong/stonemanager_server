package com.ssoggong.stonemanager_server.exception;

public class WrongStudentIdException extends WrongValueException{
    public WrongStudentIdException(String studentId) {
        super("studentId : " + studentId);
    }
}
