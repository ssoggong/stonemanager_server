package com.ssoggong.stonemanager_server.service;

import com.ssoggong.stonemanager_server.entity.Task;
import com.ssoggong.stonemanager_server.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    @Transactional
    public void saveTask(Task task) { taskRepository.save(task); }
}
