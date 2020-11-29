package com.ssoggong.stonemanager_server.dto.project;

import com.ssoggong.stonemanager_server.entity.Project;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReadProjectListDto {
    private Long projectIndex;
    private String projectName;
    private String projectSubject;
    private Double projectProgress;

    public static ReadProjectListDto of(Project project, Double projectProgress) {
        return new ReadProjectListDto(project.getIdx(), project.getProjectName(), project.getSubject().getName(), projectProgress);
    }
}
