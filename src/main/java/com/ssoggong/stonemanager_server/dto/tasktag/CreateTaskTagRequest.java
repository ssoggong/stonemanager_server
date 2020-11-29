package com.ssoggong.stonemanager_server.dto.tasktag;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateTaskTagRequest {
    private String tagName;
    private Integer tagColor;
}
