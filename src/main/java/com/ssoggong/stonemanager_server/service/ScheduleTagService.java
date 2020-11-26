package com.ssoggong.stonemanager_server.service;

import com.ssoggong.stonemanager_server.entity.ScheduleTag;
import com.ssoggong.stonemanager_server.repository.ScheduleTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ScheduleTagService {

    private final ScheduleTagRepository scheduleTagRepository;

    public void saveScheduleTag(ScheduleTag scheduleTag) { scheduleTagRepository.save(scheduleTag);}
}
