package com.ssoggong.stonemanager_server.dto.project;

import com.ssoggong.stonemanager_server.entity.Project;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectWithdrawDto {
    private Long projectIndex;
    private String projectSubject;
    private String projectTeam;
    private String projectName;

    public static ProjectWithdrawDto of(Project project){
        return new ProjectWithdrawDto(project.getIdx(), project.getSubject().getName(),
                project.getTeamName(), project.getProjectName());
    }
}
