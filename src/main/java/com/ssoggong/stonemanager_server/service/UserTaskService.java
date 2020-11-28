package com.ssoggong.stonemanager_server.service;

import com.ssoggong.stonemanager_server.entity.UserTask;
import com.ssoggong.stonemanager_server.repository.UserTaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserTaskService {

    private final UserTaskRepository userTaskRepository;

    @Transactional
    public void saveUserTask(UserTask userTask) { userTaskRepository.save(userTask); }
}
