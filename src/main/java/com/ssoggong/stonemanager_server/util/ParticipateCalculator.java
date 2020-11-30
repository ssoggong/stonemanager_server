package com.ssoggong.stonemanager_server.util;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ParticipateCalculator {
    private Long totalTaskCount;
    private Long memberDoneTaskCount;
    private int memberCount;

    public Double getParticipateRate(){
        if(totalTaskCount == 0) return 100.0/memberCount;
        return memberDoneTaskCount.doubleValue()/totalTaskCount.doubleValue();
    }
}
