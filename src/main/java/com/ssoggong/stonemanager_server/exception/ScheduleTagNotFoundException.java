package com.ssoggong.stonemanager_server.exception;

public class ScheduleTagNotFoundException extends NotFoundException {
    public ScheduleTagNotFoundException(Long scheduleTagId) {
        super(scheduleTagId.toString());
    }
}
