package com.ssoggong.stonemanager_server.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssoggong.stonemanager_server.entity.Schedule;
import com.ssoggong.stonemanager_server.entity.ScheduleScheduleTag;
import com.ssoggong.stonemanager_server.entity.ScheduleTag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReadScheduleListDto {
    private Long scheduleIndex;
    private String scheduleName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime scheduleDate;
    private List<ReadScheduleList_tagDto> tagInfo;

    public static ReadScheduleListDto of(Schedule schedule) {

        List<ReadScheduleList_tagDto> scheduleTagDto = new ArrayList<>();

        for (ScheduleScheduleTag scheduleScheduleTag : schedule.getScheduleScheduleTagSet()) {
            ScheduleTag scheduleTag = scheduleScheduleTag.getScheduleTag();
            scheduleTagDto.add(ReadScheduleList_tagDto.of(scheduleTag));
        }

        return new ReadScheduleListDto(schedule.getIdx(), schedule.getName(), schedule.getDate(), scheduleTagDto);
    }
}
