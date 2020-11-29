package com.ssoggong.stonemanager_server.dto.schedule;

import com.ssoggong.stonemanager_server.entity.ScheduleTag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReadScheduleList_tagDto {
    private Long tagIndex;
    private String tagName;
    private Integer tagColor;

    public static ReadScheduleList_tagDto of(ScheduleTag scheduleTag) {
        return new ReadScheduleList_tagDto(scheduleTag.getIdx(), scheduleTag.getName(), scheduleTag.getColor());
    }
}
