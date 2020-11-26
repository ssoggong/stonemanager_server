package com.ssoggong.stonemanager_server.service;

import com.ssoggong.stonemanager_server.entity.Schedule;
import com.ssoggong.stonemanager_server.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Transactional
    public void saveSchedule(Schedule schedule) { scheduleRepository.save(schedule); }
}
