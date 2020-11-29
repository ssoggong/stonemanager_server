package com.ssoggong.stonemanager_server.dto;

import com.ssoggong.stonemanager_server.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReadScheduleDetail_assigneeDto {
    private Long assigneeIndex;
    private String assigneeName;

    public static ReadScheduleDetail_assigneeDto of(User user) {
        return new ReadScheduleDetail_assigneeDto(user.getIdx(), user.getName());
    }
}
