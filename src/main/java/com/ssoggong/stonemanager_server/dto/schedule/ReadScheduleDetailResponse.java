package com.ssoggong.stonemanager_server.dto.schedule;

import com.ssoggong.stonemanager_server.dto.schedule.ReadScheduleDetailDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReadScheduleDetailResponse {
    private ReadScheduleDetailDto data;
}
