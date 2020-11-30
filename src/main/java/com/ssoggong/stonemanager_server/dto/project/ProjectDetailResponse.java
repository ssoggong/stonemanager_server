package com.ssoggong.stonemanager_server.dto.project;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDetailResponse {
    private String projectName;
    private String projectTeam;
    private String projectSubject;
    private String projectProfessor;
    private Double projectProgress;
    private List<ProjectParticipateDto> projectParticipateInfo;
    private List<DdayDto> DDayInfo;

}
