package com.ssoggong.stonemanager_server.dto.task;

import com.ssoggong.stonemanager_server.entity.Checklist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReadTaskDetail_checklistDto {
    private Long checklistIndex;
    private String checklistName;
    private Boolean checklistState;

    public static ReadTaskDetail_checklistDto of(Checklist checklist) {
        return new ReadTaskDetail_checklistDto(checklist.getIdx(), checklist.getName(), checklist.getState());
    }
}
