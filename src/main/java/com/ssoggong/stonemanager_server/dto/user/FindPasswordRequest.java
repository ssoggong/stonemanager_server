package com.ssoggong.stonemanager_server.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FindPasswordRequest {
    private String userName;
    private String userStudentId;
    private String userEmail;
}
