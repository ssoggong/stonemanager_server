package com.ssoggong.stonemanager_server.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProjectWithdrawDto {
    private Long projectIndex;
    private String projectSubject;
    private String projectTeam;
    private String projectName;
}
