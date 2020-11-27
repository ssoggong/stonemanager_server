package com.ssoggong.stonemanager_server.exception;

public class ScheduleNotFoundException extends RuntimeException{
    public ScheduleNotFoundException() {super("Invalid ScheduleId Exception"); }
}
