package com.ssoggong.stonemanager_server.dto.project;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProjectRequest {
    private String projectTeam;
    private String projectName;
    private List<Long> team;
}
