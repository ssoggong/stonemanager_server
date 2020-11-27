package com.ssoggong.stonemanager_server.service;

import com.ssoggong.stonemanager_server.dto.CreateScheduleTagRequest;
import com.ssoggong.stonemanager_server.dto.ReadScheduleTagResponse;
import com.ssoggong.stonemanager_server.entity.Project;
import com.ssoggong.stonemanager_server.entity.ScheduleTag;
import com.ssoggong.stonemanager_server.entity.User;
import com.ssoggong.stonemanager_server.exception.ProjectNotFoundException;
import com.ssoggong.stonemanager_server.exception.UserNotFoundException;
import com.ssoggong.stonemanager_server.repository.ProjectRepository;
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
public class ScheduleTagService {

    private final ScheduleTagRepository scheduleTagRepository;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;

    public void saveScheduleTag(ScheduleTag scheduleTag) { scheduleTagRepository.save(scheduleTag);}

    @Transactional
    public void createScheduleTag(Long userId, Long projectId, CreateScheduleTagRequest request) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        Project project = projectRepository.findById(projectId).orElseThrow(ProjectNotFoundException::new);

        ScheduleTag scheduleTag = ScheduleTag.builder()
                .name(request.getTagName())
                .color(request.getTagColor())
                .scheduleScheduleTagSet(new HashSet<>())
                .build();

        saveScheduleTag(scheduleTag);
    }

    public ReadScheduleTagResponse readScheduleTag(Long userId, Long projectId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        Project project = projectRepository.findById(projectId).orElseThrow(ProjectNotFoundException::new);

        Set<ScheduleTag> scheduleTagList = project.getS();

        return response;
    }
}
