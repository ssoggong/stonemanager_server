package com.ssoggong.stonemanager_server.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserRequest {
    private String userName;
    private String studentId;
    private String password;
    private String email;
}
