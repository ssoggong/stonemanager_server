package com.ssoggong.stonemanager_server.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectWithdrawResponse {
    private List<ProjectWithdrawDto> data;
}
