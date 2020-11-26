package com.ssoggong.stonemanager_server.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ProjectWithdrawResponse {
    private List<ProjectWithdrawDto> data;
}
