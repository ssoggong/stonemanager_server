package com.ssoggong.stonemanager_server.dto.task;

import com.ssoggong.stonemanager_server.entity.TaskTag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReadTaskDetail_tagDto {
    private Long tagIndex;
    private String tagName;
    private Integer tagColor;

    public static ReadTaskDetail_tagDto of(TaskTag taskTag) {
        return new ReadTaskDetail_tagDto(taskTag.getIdx(), taskTag.getName(), taskTag.getColor());
    }
}
