package com.ssoggong.stonemanager_server.dto.project;

import com.ssoggong.stonemanager_server.dto.project.ReadProjectListDto;
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
