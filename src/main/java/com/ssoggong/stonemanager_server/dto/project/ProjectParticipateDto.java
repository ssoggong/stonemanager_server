package com.ssoggong.stonemanager_server.dto.project;

import com.ssoggong.stonemanager_server.entity.Project;
import com.ssoggong.stonemanager_server.entity.ProjectUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectParticipateDto {
    private Long memberIndex;
    private String memberName;
    private Double participateRate;

    public static ProjectParticipateDto of(ProjectUser projectUser, Double memberParticipateRate){
        return new ProjectParticipateDto(projectUser.getUser().getIdx(), projectUser.getUser().getName(), memberParticipateRate);
    }
}
