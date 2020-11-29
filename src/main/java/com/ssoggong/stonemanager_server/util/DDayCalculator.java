package com.ssoggong.stonemanager_server.util;

import java.time.LocalDateTime;

import static java.time.temporal.ChronoUnit.DAYS;

public class DDayCalculator {
    private LocalDateTime toDate;
    LocalDateTime fromDate = LocalDateTime.now();

    public DDayCalculator(LocalDateTime date){
        this.toDate = date;
    }

    public Long getDDay(){
        return DAYS.between(fromDate, toDate);
    }
}
