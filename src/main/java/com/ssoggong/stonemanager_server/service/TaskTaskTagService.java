package com.ssoggong.stonemanager_server.service;

import com.ssoggong.stonemanager_server.entity.TaskTaskTag;
import com.ssoggong.stonemanager_server.repository.TaskTaskTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TaskTaskTagService {

    private final TaskTaskTagRepository taskTaskTagRepository;

    @Transactional
    public void saveTaskTaskTag(TaskTaskTag taskTaskTag) { taskTaskTagRepository.save(taskTaskTag);}
}
