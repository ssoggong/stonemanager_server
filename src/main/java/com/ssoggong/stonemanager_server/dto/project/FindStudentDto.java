package com.ssoggong.stonemanager_server.dto.project;

import com.ssoggong.stonemanager_server.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FindStudentDto {
    private Long userId;
    private String userName;
    private String userStudentId;

    public static FindStudentDto of(User user){
        return new FindStudentDto(user.getIdx(), user.getName(), user.getStudentId());
    }
}
