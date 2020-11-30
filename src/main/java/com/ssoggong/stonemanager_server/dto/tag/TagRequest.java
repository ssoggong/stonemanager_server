package com.ssoggong.stonemanager_server.dto.tag;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TagRequest {
    private String tagName;
    private Integer tagColor;
}
