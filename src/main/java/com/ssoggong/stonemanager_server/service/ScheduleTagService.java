package com.ssoggong.stonemanager_server.service;

import com.ssoggong.stonemanager_server.dto.scheduleTag.CreateScheduleTagRequest;
import com.ssoggong.stonemanager_server.dto.scheduleTag.ReadScheduleTagDto;
import com.ssoggong.stonemanager_server.dto.scheduleTag.ReadScheduleTagResponse;
import com.ssoggong.stonemanager_server.entity.Project;
import com.ssoggong.stonemanager_server.entity.ScheduleTag;
import com.ssoggong.stonemanager_server.entity.User;
import com.ssoggong.stonemanager_server.exception.ProjectNotFoundException;
import com.ssoggong.stonemanager_server.exception.ScheduleTagNotFoundException;
import com.ssoggong.stonemanager_server.exception.UserNotFoundException;
import com.ssoggong.stonemanager_server.repository.ProjectRepository;
import com.ssoggong.stonemanager_server.repository.ScheduleTagRepository;
import com.ssoggong.stonemanager_server.repository.UserRepository;
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
public class ScheduleTagService {

    private final ScheduleTagRepository scheduleTagRepository;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;

    public void saveScheduleTag(ScheduleTag scheduleTag) { scheduleTagRepository.save(scheduleTag);}

    @Transactional
    public void createScheduleTag(Long userId, Long projectId, CreateScheduleTagRequest request) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException(projectId));

        ScheduleTag scheduleTag = ScheduleTag.builder()
                .name(request.getTagName())
                .color(request.getTagColor())
                .scheduleScheduleTagSet(new HashSet<>())
                .build();

        saveScheduleTag(scheduleTag);
    }

    public ReadScheduleTagResponse readScheduleTag(Long userId, Long projectId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException(projectId));
        Set<ScheduleTag> scheduleTagList = project.getScheduleTagSet();

        List<ReadScheduleTagDto> dto = new ArrayList<>();
        for(ScheduleTag scheduleTag: scheduleTagList) {
            dto.add(ReadScheduleTagDto.of(scheduleTag));
        }
        return new ReadScheduleTagResponse(dto);
    }

    @Transactional
    public void updateScheduleTag(Long userId, Long projectId, Long scheduleTagId, CreateScheduleTagRequest request) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException(projectId));
        ScheduleTag scheduleTag = scheduleTagRepository.findById(scheduleTagId).orElseThrow(() -> new ScheduleTagNotFoundException(scheduleTagId));

        scheduleTag.setName(request.getTagName());
        scheduleTag.setColor(request.getTagColor());

        saveScheduleTag(scheduleTag);
    }

    @Transactional
    public void deleteScheduleTag(Long userId, Long projectId, Long scheduleTagId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException(projectId));
        ScheduleTag scheduleTag = scheduleTagRepository.findById(scheduleTagId).orElseThrow(() -> new ScheduleTagNotFoundException(scheduleTagId));
        scheduleTagRepository.delete(scheduleTag);
    }
}
