package com.ssoggong.stonemanager_server.exception;

public class ScheduleNotFoundException extends RuntimeException{
    public ScheduleNotFoundException(Long scheduleId) {
        super("Invalid ScheduleId Exception: " + scheduleId);
        System.out.println(getMessage());
    }
}
