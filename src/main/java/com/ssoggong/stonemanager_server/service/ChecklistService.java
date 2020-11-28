package com.ssoggong.stonemanager_server.service;

import com.ssoggong.stonemanager_server.entity.Checklist;
import com.ssoggong.stonemanager_server.repository.ChecklistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChecklistService {

    private final ChecklistRepository checklistRepository;

    @Transactional
    public void saveChecklist(Checklist checklist) { checklistRepository.save(checklist); }
}
