package com.ssoggong.stonemanager_server.dto.task;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReadTaskListResponse {
    private List<ReadTaskList_taskDto> taskInfo;
}
