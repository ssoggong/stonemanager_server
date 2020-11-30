package com.ssoggong.stonemanager_server.dto.checklist;

import com.ssoggong.stonemanager_server.entity.Checklist;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ChecklistDto {
    private Long checklistIndex;
    private String checklistName;
    private Boolean checklistState;

    public static ChecklistDto of(Checklist checklist) {
        return new ChecklistDto(checklist.getIdx(), checklist.getName(), checklist.getState());
    }
}
