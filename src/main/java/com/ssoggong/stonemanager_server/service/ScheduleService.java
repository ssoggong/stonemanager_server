package com.ssoggong.stonemanager_server.service;

import com.ssoggong.stonemanager_server.dto.*;
import com.ssoggong.stonemanager_server.entity.*;
import com.ssoggong.stonemanager_server.exception.ProjectNotFoundException;
import com.ssoggong.stonemanager_server.exception.ScheduleNotFoundException;
import com.ssoggong.stonemanager_server.exception.ScheduleTagNotFoundException;
import com.ssoggong.stonemanager_server.exception.UserNotFoundException;
import com.ssoggong.stonemanager_server.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    private final UserScheduleRepository userScheduleRepository;
    private final ScheduleScheduleTagRepository scheduleScheduleTagRepository;


    @Transactional
    public void saveSchedule(Schedule schedule) { scheduleRepository.save(schedule); }

    @Transactional
    public void createSchedule(Long userId, Long projectId, CreateScheduleRequest request) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        List<Long> assigneeIdList =  request.getScheduleAssigneeIdList();
        List<Long> scheduleTagIdList = request.getScheduleTagIdList();

        //== 프로젝트 생성 ==//
        Project project = projectRepository.findById(projectId).orElseThrow();
        Schedule schedule = Schedule.builder()
                .name(request.getScheduleName())
                .date(request.getScheduleDate())
                .description(request.getScheduleDescription())
                .project(project)
                .userScheduleSet(new HashSet<>())
                .scheduleScheduleTagSet(new HashSet<>())
                .build();

        //== userSchedule 생성 및 연관관계 설정 ==//
        for(Long assigneeId : assigneeIdList) {
            User assignee = userRepository.findById(assigneeId).orElseThrow(UserNotFoundException::new);
            UserSchedule userSchedule = UserSchedule.builder()
                    .user(assignee)
                    .schedule(schedule)
                    .build();
        }

        //== scheduleScheduleTag 생성 및 연관관계 설정 ==//
        for(Long scheduleTagId: scheduleTagIdList) {
            ScheduleTag scheduleTag = scheduleTagRepository.findById(scheduleTagId).orElseThrow(ScheduleTagNotFoundException::new);
            ScheduleScheduleTag scheduleScheduleTag = ScheduleScheduleTag.builder()
                    .scheduleTag(scheduleTag)
                    .schedule(schedule)
                    .build();
        }

        saveSchedule(schedule);
    }

    public ReadScheduleListResponse readSchedule(Long userId, Long projectId, int year, int month) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        Project project = projectRepository.findById(projectId).orElseThrow(ProjectNotFoundException::new);
        List<Schedule> scheduleList = scheduleRepository.readScheduleByYearAndMonth(year, month, projectId);

        List<ReadScheduleListDto> dto = new ArrayList<>();
        for(Schedule schedule: scheduleList) {
            dto.add(ReadScheduleListDto.of(schedule));
        }

        return new ReadScheduleListResponse(dto);
    }

    @Transactional
    public void deleteSchedule(Long userId, Long projectId, Long scheduleId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        Project project = projectRepository.findById(projectId).orElseThrow(ProjectNotFoundException::new);
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(ScheduleNotFoundException::new);

        scheduleRepository.deleteById(scheduleId);
    }

    public ReadScheduleDetailResponse readScheduleDetail(Long userId, Long projectId, Long scheduleId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        Project project = projectRepository.findById(projectId).orElseThrow(ProjectNotFoundException::new);
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(ScheduleNotFoundException::new);

        ReadScheduleDetailDto dto = ReadScheduleDetailDto.of(schedule);

        return new ReadScheduleDetailResponse(dto);
    }
}
