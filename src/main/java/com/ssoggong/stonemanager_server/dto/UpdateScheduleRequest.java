package com.ssoggong.stonemanager_server.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateScheduleRequest {
    private String scheduleName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime scheduleDate;
    private String scheduleDescription;
    private List<Long> scheduleAssigneeIdList;
    private List<Long> scheduleTagIdList;
}
