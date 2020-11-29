package com.ssoggong.stonemanager_server.dto.schedule;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssoggong.stonemanager_server.entity.Schedule;
import com.ssoggong.stonemanager_server.entity.ScheduleScheduleTag;
import com.ssoggong.stonemanager_server.entity.UserSchedule;
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
public class ReadScheduleDetailDto {
    private String scheduleName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime scheduleDate;
    private String scheduleDescription;
    private List<ReadScheduleDetail_assigneeDto> assigneeInfo;
    private List<ReadScheduleDetail_tagDto> tagInfo;

    public static ReadScheduleDetailDto of(Schedule schedule) {

        List<ReadScheduleDetail_assigneeDto> assigneeDto = new ArrayList<>();
        List<ReadScheduleDetail_tagDto> tagDto = new ArrayList<>();

        for(UserSchedule userSchedule: schedule.getUserScheduleSet()) {
            assigneeDto.add(ReadScheduleDetail_assigneeDto.of(userSchedule.getUser()));
        }

        for(ScheduleScheduleTag scheduleScheduleTag: schedule.getScheduleScheduleTagSet()) {
            tagDto.add(ReadScheduleDetail_tagDto.of(scheduleScheduleTag.getScheduleTag()));
        }

        return new ReadScheduleDetailDto(schedule.getName(), schedule.getDate(), schedule.getDescription(), assigneeDto, tagDto);
    }
}
