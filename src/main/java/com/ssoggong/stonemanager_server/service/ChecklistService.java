package com.ssoggong.stonemanager_server.service;

import com.ssoggong.stonemanager_server.dto.checklist.ChecklistDto;
import com.ssoggong.stonemanager_server.dto.checklist.ChecklistRequest;
import com.ssoggong.stonemanager_server.dto.checklist.ChecklistResponse;
import com.ssoggong.stonemanager_server.entity.*;
import com.ssoggong.stonemanager_server.exception.ChecklistNotFoundException;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChecklistService {

    private final ChecklistRepository checklistRepository;

    @Transactional
    public void saveChecklist(Checklist checklist) { checklistRepository.save(checklist); }

    public Checklist findById(Long checklistId) {
        return checklistRepository.findById(checklistId).orElseThrow(() -> new ChecklistNotFoundException(checklistId));
    }

    @Transactional
    public ChecklistResponse createChecklist(Task task, ChecklistRequest checklistRequest) {
        Checklist checklist = Checklist.builder()
                .name(checklistRequest.getChecklistName())
                .state(checklistRequest.getChecklistState())
                .task(task)
                .build();
        Long checklistId = checklistRepository.save(checklist).getIdx();

        return new ChecklistResponse(checklistId);
    }

    @Transactional
    public void updateChecklist(Checklist checklist, ChecklistRequest checklistRequest) {
        checklist.setName(checklistRequest.getChecklistName());
        checklist.setState(checklistRequest.getChecklistState());
        checklistRepository.save(checklist);
    }

    @Transactional
    public void deleteChecklist(Checklist checklist) {
        checklistRepository.delete(checklist);
    }

    public List<ChecklistDto> readChecklist(Task task) {
        Set<Checklist> checklistSet = task.getChecklistSet();

        List<ChecklistDto> checklistDtoList = new ArrayList<>();
        for(Checklist checklist: checklistSet) {
            checklistDtoList.add(ChecklistDto.of(checklist));
        }

        return checklistDtoList;
    }
}
