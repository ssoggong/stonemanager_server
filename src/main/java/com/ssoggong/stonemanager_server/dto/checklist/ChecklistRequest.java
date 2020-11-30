package com.ssoggong.stonemanager_server.dto.checklist;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChecklistRequest {
    private String checklistName;
    private Boolean checklistState;
}
