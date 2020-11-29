package com.ssoggong.stonemanager_server.service;

import com.ssoggong.stonemanager_server.dto.checklist.ChecklistRequest;
import com.ssoggong.stonemanager_server.entity.*;
import com.ssoggong.stonemanager_server.exception.ProjectNotFoundException;
import com.ssoggong.stonemanager_server.exception.TaskTagNotFoundException;
import com.ssoggong.stonemanager_server.exception.UserNotFoundException;
import com.ssoggong.stonemanager_server.repository.ChecklistRepository;
import com.ssoggong.stonemanager_server.repository.ProjectRepository;
import com.ssoggong.stonemanager_server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Check;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChecklistService {

    private final ChecklistRepository checklistRepository;

    @Transactional
    public void saveChecklist(Checklist checklist) { checklistRepository.save(checklist); }

    @Transactional
    public void createChecklist(Task task, ChecklistRequest checklistRequest) {
        Checklist checklist = Checklist.builder()
                .name(checklistRequest.getChecklistName())
                .state(checklistRequest.getChecklistState())
                .task(task)
                .build();
        saveChecklist(checklist);
    }
}
