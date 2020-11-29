package com.ssoggong.stonemanager_server.exception;

public class ScheduleNotFoundException extends NotFoundException{
    public ScheduleNotFoundException(Long scheduleId) {
        super(" scheduleId : " + scheduleId.toString());
    }
}
