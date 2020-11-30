package com.ssoggong.stonemanager_server.dto.tag;

import com.ssoggong.stonemanager_server.entity.ScheduleTag;
import com.ssoggong.stonemanager_server.entity.TaskTag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TagDto {
    private Long tagIndex;
    private String tagName;
    private Integer tagColor;

    public static TagDto of(ScheduleTag scheduleTag){
        return new TagDto(scheduleTag.getIdx(), scheduleTag.getName(), scheduleTag.getColor());
    }

    public static TagDto of(TaskTag taskTag){
        return new TagDto(taskTag.getIdx(), taskTag.getName(), taskTag.getColor());
    }
}
