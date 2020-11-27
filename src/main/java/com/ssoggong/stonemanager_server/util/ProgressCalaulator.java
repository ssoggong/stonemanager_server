package com.ssoggong.stonemanager_server.util;

public class ProgressCalaulator {
    private Long totalTaskCount;
    private Long doneTaskCount;

    public ProgressCalaulator(Long totalTaskCount, Long doneTaskCount){
        this.totalTaskCount = totalTaskCount;
        this.doneTaskCount = doneTaskCount;
    }

    public Double getProgressRate(){
        if(totalTaskCount == 0) return 0.;
        return (doneTaskCount.doubleValue()/totalTaskCount.doubleValue())*100;
    }
}
