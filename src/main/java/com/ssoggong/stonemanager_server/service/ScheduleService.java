package com.ssoggong.stonemanager_server.service;

import com.ssoggong.stonemanager_server.dto.CreateScheduleRequest;
import com.ssoggong.stonemanager_server.entity.*;
import com.ssoggong.stonemanager_server.exception.ScheduleTagNotFoundException;
import com.ssoggong.stonemanager_server.exception.UserNotFoundException;
import com.ssoggong.stonemanager_server.repository.ProjectRepository;
import com.ssoggong.stonemanager_server.repository.ScheduleRepository;
import com.ssoggong.stonemanager_server.repository.ScheduleTagRepository;
import com.ssoggong.stonemanager_server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final ScheduleTagRepository scheduleTagRepository;


    @Transactional
    public void saveSchedule(Schedule schedule) { scheduleRepository.save(schedule); }

    @Transactional
    public void createSchedule(Long userId, Long projectId, CreateScheduleRequest request) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        List<Long> assigneeIdList =  request.getScheduleAssigneeIdList();
        List<Long> scheduleTagIdList = request.getScheduleTagIdList();

        Set<UserSchedule> userScheduleSet = new HashSet<>();
        for(Long assigneeId : assigneeIdList) {
            User assignee = userRepository.findById(assigneeId).orElseThrow(UserNotFoundException::new);
            UserSchedule userSchedule = UserSchedule.builder()
                    .user(assignee)
                    .build();

            userScheduleSet.add(userSchedule);
        }

        Set<ScheduleScheduleTag> scheduleScheduleTagSet = new HashSet<>();
        for(Long scheduleTagId: scheduleTagIdList) {
            ScheduleTag scheduleTag = scheduleTagRepository.findById(scheduleTagId).orElseThrow(ScheduleTagNotFoundException::new);
            ScheduleScheduleTag scheduleScheduleTag = ScheduleScheduleTag.builder()
                    .scheduleTag(scheduleTag)
                    .build();

            scheduleScheduleTagSet.add(scheduleScheduleTag);
        }

        Project project = projectRepository.findById(projectId).orElseThrow();
        Schedule schedule = Schedule.builder()
                .name(request.getScheduleName())
                .date(request.getScheduleDate())
                .description(request.getScheduleDescription())
                .userScheduleSet(userScheduleSet)
                .scheduleScheduleTagSet(scheduleScheduleTagSet)
                .project(project)
                .build();

        saveSchedule(schedule);
    }
}
