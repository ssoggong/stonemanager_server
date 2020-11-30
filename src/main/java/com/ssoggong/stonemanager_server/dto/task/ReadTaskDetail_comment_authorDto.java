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
public class ReadTaskDetail_comment_authorDto {
    private Long userIndex;
    private String userName;
    private String userImage;

    public static ReadTaskDetail_comment_authorDto of(User user){
        return new ReadTaskDetail_comment_authorDto(user.getIdx(), user.getName(), user.getImage());
    }
}
