package com.ssoggong.stonemanager_server.dto.task;

import com.ssoggong.stonemanager_server.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReadTaskList_task_assigneeDto {
    private Long userIndex;
    private String userName;

    public static ReadTaskList_task_assigneeDto of(User user) {
        return new ReadTaskList_task_assigneeDto(user.getIdx(), user.getName());
    }
}
