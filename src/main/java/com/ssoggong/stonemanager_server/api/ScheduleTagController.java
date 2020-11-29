package com.ssoggong.stonemanager_server.api;

import com.ssoggong.stonemanager_server.api.constants.Message;
import com.ssoggong.stonemanager_server.api.constants.ResponseMessage;
import com.ssoggong.stonemanager_server.api.constants.StatusCode;
import com.ssoggong.stonemanager_server.dto.tag.TagRequest;
import com.ssoggong.stonemanager_server.dto.tag.TagResponse;
import com.ssoggong.stonemanager_server.entity.Project;
import com.ssoggong.stonemanager_server.entity.ScheduleTag;
import com.ssoggong.stonemanager_server.entity.User;
import com.ssoggong.stonemanager_server.service.ProjectService;
import com.ssoggong.stonemanager_server.service.ScheduleTagService;
import com.ssoggong.stonemanager_server.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/scheduleTag")
public class ScheduleTagController {

    private final ScheduleTagService scheduleTagService;
    private final UserService userService;
    private final ProjectService projectService;

    @PostMapping
    public ResponseEntity<Message> createScheduleTag(@RequestHeader("userIndex") Long userId, @RequestHeader("projectIndex") Long projectId, @RequestBody TagRequest request) {
        User user = userService.findById(userId);
        Project project = projectService.findById(projectId);
        scheduleTagService.createScheduleTag(request);
        Message message = new Message(StatusCode.OK, ResponseMessage.CREATE_SCHEDULE_TAG);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Message> readScheduleTag(@RequestHeader("userIndex") Long userId, @RequestHeader("projectIndex") Long projectId){
        User user = userService.findById(userId);
        Project project = projectService.findById(projectId);
        TagResponse response = scheduleTagService.readScheduleTag(project);
        Message message = new Message(StatusCode.OK, ResponseMessage.READ_SCHEDULE_TAG, response);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PutMapping("/{tagIndex}")
    public ResponseEntity<Message> updateScheduleTag(@RequestHeader("userIndex") Long userId, @RequestHeader("projectIndex") Long projectId, @PathVariable("tagIndex") Long scheduleTagId,@RequestBody TagRequest request) {
        User user = userService.findById(userId);
        Project project = projectService.findById(projectId);
        ScheduleTag scheduleTag = scheduleTagService.findById(scheduleTagId);
        scheduleTagService.updateScheduleTag(scheduleTag, request);
        Message message = new Message(StatusCode.OK, ResponseMessage.UPDATE_SCHEDULE_TAG);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @DeleteMapping("/{tagIndex}")
    public ResponseEntity<Message> deleteScheduleTag(@RequestHeader("userIndex") Long userId, @RequestHeader("projectIndex") Long projectId, @PathVariable("tagIndex") Long scheduleTagId) {
        User user = userService.findById(userId);
        Project project = projectService.findById(projectId);
        ScheduleTag scheduleTag = scheduleTagService.findById(scheduleTagId);
        scheduleTagService.deleteScheduleTag(scheduleTag);
        Message message = new Message(StatusCode.OK, ResponseMessage.DELETE_SCHEDULE_TAG);
        return new ResponseEntity<>(message, HttpStatus.OK);

    }
}
