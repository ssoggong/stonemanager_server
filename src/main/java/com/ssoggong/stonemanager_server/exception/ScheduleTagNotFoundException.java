package com.ssoggong.stonemanager_server.exception;

public class ScheduleTagNotFoundException extends RuntimeException {
    public ScheduleTagNotFoundException(Long scheduleTagId) {
        super("Invalid ScheduleTagId Exception : " + scheduleTagId);
        System.out.println(getMessage());
    }
}
