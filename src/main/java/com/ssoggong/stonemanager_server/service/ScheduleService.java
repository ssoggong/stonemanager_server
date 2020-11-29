package com.ssoggong.stonemanager_server.service;

import com.ssoggong.stonemanager_server.dto.schedule.*;
import com.ssoggong.stonemanager_server.entity.*;
import com.ssoggong.stonemanager_server.exception.*;
import com.ssoggong.stonemanager_server.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

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
    private final UserScheduleService userScheduleService;
    private final ScheduleScheduleTagService scheduleScheduleTagService;


    @Transactional
    public void saveSchedule(Schedule schedule) { scheduleRepository.save(schedule); }

    public Schedule findById(Long scheduleId){
        return scheduleRepository.findById(scheduleId).orElseThrow(() -> new ScheduleNotFoundException(scheduleId));
    }

    @Transactional
    public void createSchedule(Project project, CreateScheduleRequest request) {
        List<Long> assigneeIdList =  request.getScheduleAssigneeIdList();
        List<Long> scheduleTagIdList = request.getScheduleTagIdList();

        //== 일정 생성 ==//
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
            User assignee = userRepository.findById(assigneeId).orElseThrow(() -> new UserNotFoundException(assigneeId));
            UserSchedule userSchedule = UserSchedule.builder()
                    .user(assignee)
                    .schedule(schedule)
                    .build();
        }

        //== scheduleScheduleTag 생성 및 연관관계 설정 ==//
        for(Long scheduleTagId: scheduleTagIdList) {
            ScheduleTag scheduleTag = scheduleTagRepository.findById(scheduleTagId).orElseThrow(() -> new ScheduleTagNotFoundException(scheduleTagId));
            ScheduleScheduleTag scheduleScheduleTag = ScheduleScheduleTag.builder()
                    .scheduleTag(scheduleTag)
                    .schedule(schedule)
                    .build();
        }

        saveSchedule(schedule);
    }

    public ReadScheduleListResponse readSchedule(Long projectId, int year, int month) {
        List<Schedule> scheduleList = scheduleRepository.readScheduleByYearAndMonth(year, month, projectId);

        List<ReadScheduleListDto> dto = new ArrayList<>();
        for(Schedule schedule: scheduleList) {
            dto.add(ReadScheduleListDto.of(schedule));
        }

        return new ReadScheduleListResponse(dto);
    }

    @Transactional
    public void deleteSchedule(Long scheduleId) {
        scheduleRepository.deleteById(scheduleId);
    }

    public ReadScheduleDetailResponse readScheduleDetail(Schedule schedule) {
        ReadScheduleDetailDto dto = ReadScheduleDetailDto.of(schedule);

        return new ReadScheduleDetailResponse(dto);
    }

    @Transactional
    public void updateSchedule(Schedule schedule, UpdateScheduleRequest request) {
        schedule.setName(request.getScheduleName());
        schedule.setDate(request.getScheduleDate());
        schedule.setDescription(request.getScheduleDescription());

        for(UserSchedule userSchedule: schedule.getUserScheduleSet()) {
            userSchedule.setSchedule(null);
            userScheduleRepository.delete(userSchedule);
        }

        for(ScheduleScheduleTag scheduleScheduleTag: schedule.getScheduleScheduleTagSet()) {
            scheduleScheduleTag.setSchedule(null);
            scheduleScheduleTagRepository.delete(scheduleScheduleTag);
        }

        for(Long assigneeId: request.getScheduleAssigneeIdList()) {
            User assignee = userRepository.findById(assigneeId).orElseThrow(() -> new UserNotFoundException(assigneeId));
            UserSchedule userSchedule = UserSchedule.builder()
                    .user(assignee)
                    .schedule(schedule)
                    .build();
        }

        for(Long scheduleTagId: request.getScheduleTagIdList()) {
            System.out.println(scheduleTagId);
            ScheduleTag scheduleTag = scheduleTagRepository.findById(scheduleTagId).orElseThrow(() -> new ScheduleTagNotFoundException(scheduleTagId));
            ScheduleScheduleTag scheduleScheduleTag = ScheduleScheduleTag.builder()
                    .scheduleTag(scheduleTag)
                    .schedule(schedule)
                    .build();
        }

        scheduleRepository.save(schedule);
    }
}
