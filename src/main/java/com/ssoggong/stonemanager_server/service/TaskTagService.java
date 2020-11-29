package com.ssoggong.stonemanager_server.service;

import com.ssoggong.stonemanager_server.entity.TaskTag;
import com.ssoggong.stonemanager_server.exception.TaskTagNotFoundException;
import com.ssoggong.stonemanager_server.repository.TaskTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TaskTagService {

    private final TaskTagRepository taskTagRepository;

    @Transactional
    public void saveTaskTag(TaskTag taskTag) { taskTagRepository.save(taskTag);}

    public TaskTag findById(Long taskTagId) { return taskTagRepository.findById(taskTagId).orElseThrow(() -> new TaskTagNotFoundException(taskTagId));}
}
