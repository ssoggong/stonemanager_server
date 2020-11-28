package com.ssoggong.stonemanager_server.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReadProjectListResponse {
    private List<ReadProjectListDto> projectInfo;
}
