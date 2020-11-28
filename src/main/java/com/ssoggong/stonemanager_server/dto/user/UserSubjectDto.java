package com.ssoggong.stonemanager_server.dto.user;

import com.ssoggong.stonemanager_server.entity.Subject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserSubjectDto {
    private Long subjectId;
    private String subjectName;

    public static UserSubjectDto of(Subject subject){
        return new UserSubjectDto(subject.getIdx(), subject.getName());
    }
}
