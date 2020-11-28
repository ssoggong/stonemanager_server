package com.ssoggong.stonemanager_server.service;

import com.ssoggong.stonemanager_server.entity.ScheduleScheduleTag;
import com.ssoggong.stonemanager_server.repository.ScheduleScheduleTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ScheduleScheduleTagService {

    private final ScheduleScheduleTagRepository scheduleScheduleTagRepository;

    @Transactional
    public void saveScheduleScheduleTag(ScheduleScheduleTag scheduleScheduleTag) {scheduleScheduleTagRepository.save(scheduleScheduleTag); }
}
