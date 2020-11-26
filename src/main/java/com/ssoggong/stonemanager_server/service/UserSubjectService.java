package com.ssoggong.stonemanager_server.service;

import com.ssoggong.stonemanager_server.entity.UserSchedule;
import com.ssoggong.stonemanager_server.repository.UserScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserSubjectService {

    private final UserScheduleRepository userScheduleRepository;

    @Transactional
    public void saveUserScheduleRepository(UserSchedule userSchedule) { userScheduleRepository.save(userSchedule); }
}
