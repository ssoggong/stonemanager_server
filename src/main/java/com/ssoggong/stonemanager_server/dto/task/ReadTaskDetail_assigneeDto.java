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
public class ReadTaskDetail_assigneeDto {
    private Long userIndex;
    private String userName;

    public static ReadTaskDetail_assigneeDto of(User user) {
        return new ReadTaskDetail_assigneeDto(user.getIdx(), user.getName());
    }
}
