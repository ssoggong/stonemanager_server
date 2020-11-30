package com.ssoggong.stonemanager_server.service;

import com.ssoggong.stonemanager_server.dto.tag.TagRequest;
import com.ssoggong.stonemanager_server.dto.tag.TagDto;
import com.ssoggong.stonemanager_server.dto.tag.TagResponse;
import com.ssoggong.stonemanager_server.entity.Project;
import com.ssoggong.stonemanager_server.entity.ScheduleTag;
import com.ssoggong.stonemanager_server.exception.ScheduleTagNotFoundException;
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

    public ScheduleTag findById(Long scheduleTagId) {
        return scheduleTagRepository.findById(scheduleTagId).orElseThrow( ()-> new ScheduleTagNotFoundException(scheduleTagId));
    }

    @Transactional
    public void createScheduleTag(TagRequest request) {
        ScheduleTag scheduleTag = ScheduleTag.builder()
                .name(request.getTagName())
                .color(request.getTagColor())
                .scheduleScheduleTagSet(new HashSet<>())
                .build();

        saveScheduleTag(scheduleTag);
    }

    public TagResponse readScheduleTag(Project project) {
        Set<ScheduleTag> scheduleTagList = project.getScheduleTagSet();

        List<TagDto> dto = new ArrayList<>();
        for(ScheduleTag scheduleTag: scheduleTagList) {
            dto.add(TagDto.of(scheduleTag));
        }
        return new TagResponse(dto);
    }

    @Transactional
    public void updateScheduleTag(ScheduleTag scheduleTag, TagRequest request) {

        scheduleTag.setName(request.getTagName());
        scheduleTag.setColor(request.getTagColor());

        saveScheduleTag(scheduleTag);
    }

    @Transactional
    public void deleteScheduleTag(ScheduleTag scheduleTag) {
        scheduleTagRepository.delete(scheduleTag);
    }
}
