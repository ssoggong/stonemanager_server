package com.ssoggong.stonemanager_server.dto.scheduleTag;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateScheduleTagRequest {
    private String tagName;
    private Integer tagColor;
}
