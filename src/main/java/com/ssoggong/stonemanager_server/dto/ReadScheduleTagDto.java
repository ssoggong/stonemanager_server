package com.ssoggong.stonemanager_server.dto;

import com.ssoggong.stonemanager_server.entity.ScheduleTag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReadScheduleTagDto {
    private Long tagIndex;
    private String tagName;
    private Integer tagColor;

    public static ReadScheduleTagDto of(ScheduleTag scheduleTag){
        return new ReadScheduleTagDto(scheduleTag.getIdx(), scheduleTag.getName(), scheduleTag.getColor());
    }
}
