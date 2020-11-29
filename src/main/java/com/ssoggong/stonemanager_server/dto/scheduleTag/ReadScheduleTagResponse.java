package com.ssoggong.stonemanager_server.dto.scheduleTag;

import com.ssoggong.stonemanager_server.dto.scheduleTag.ReadScheduleTagDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReadScheduleTagResponse {
    private List<ReadScheduleTagDto> tagInfo;
}
