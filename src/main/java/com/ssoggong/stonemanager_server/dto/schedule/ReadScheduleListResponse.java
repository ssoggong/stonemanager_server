package com.ssoggong.stonemanager_server.dto.schedule;

import com.ssoggong.stonemanager_server.dto.schedule.ReadScheduleListDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReadScheduleListResponse {
    private List<ReadScheduleListDto> scheduleInfo;
}
