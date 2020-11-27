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
public class ReadScheduleDetail_tagDto {
    private Long tagIndex;
    private String tagName;
    private Integer tagColor;

    public static ReadScheduleDetail_tagDto of(ScheduleTag scheduleTag) {
        return new ReadScheduleDetail_tagDto(scheduleTag.getIdx(), scheduleTag.getName(), scheduleTag.getColor());
    }

}
