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
public class ProjectMemberDto {
    private Long userIndex;
    private String userName;

    public static ProjectMemberDto of(User user){
        return new ProjectMemberDto(user.getIdx(), user.getName());
    }
}
