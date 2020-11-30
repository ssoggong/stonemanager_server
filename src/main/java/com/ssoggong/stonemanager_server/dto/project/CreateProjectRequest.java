package com.ssoggong.stonemanager_server.dto.project;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateProjectRequest {
    private String subjectName;
    private String projectTeam;
    private String projectName;
    private List<Long> team;
}
