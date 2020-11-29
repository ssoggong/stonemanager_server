package com.ssoggong.stonemanager_server.util;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ParticipateCalculator {
    private Long totalTaskCount;
    private Long memberDoneTaskCount;

    public Double getParticipateRate(){
        if(totalTaskCount == 0) return 0.;
        return memberDoneTaskCount.doubleValue()/totalTaskCount.doubleValue();
    }
}
