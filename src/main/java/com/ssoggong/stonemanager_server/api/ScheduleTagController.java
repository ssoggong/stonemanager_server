package com.ssoggong.stonemanager_server.api;

import com.ssoggong.stonemanager_server.api.constants.Message;
import com.ssoggong.stonemanager_server.api.constants.ResponseMessage;
import com.ssoggong.stonemanager_server.api.constants.StatusCode;
import com.ssoggong.stonemanager_server.dto.scheduleTag.CreateScheduleTagRequest;
import com.ssoggong.stonemanager_server.dto.scheduleTag.ReadScheduleTagResponse;
import com.ssoggong.stonemanager_server.service.ScheduleTagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/scheduleTag")
public class ScheduleTagController {

    private final ScheduleTagService scheduleTagService;

    @PostMapping
    public ResponseEntity<Message> createScheduleTag(@RequestHeader("userIndex") Long userId, @RequestHeader("projectIndex") Long projectId, @RequestBody CreateScheduleTagRequest request) {
        scheduleTagService.createScheduleTag(userId, projectId, request);
        Message message = new Message(StatusCode.OK, ResponseMessage.CREATE_SCHEDULE_TAG);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Message> readScheduleTag(@RequestHeader("userIndex") Long userId, @RequestHeader("projectIndex") Long projectId){
        ReadScheduleTagResponse response = scheduleTagService.readScheduleTag(userId,projectId);
        Message message = new Message(StatusCode.OK, ResponseMessage.READ_SCHEDULE_TAG, response);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PutMapping("/{tagIndex}")
    public ResponseEntity<Message> updateScheduleTag(@RequestHeader("userIndex") Long userId, @RequestHeader("projectIndex") Long projectId, @PathVariable("tagIndex") Long scheduleTagId,@RequestBody CreateScheduleTagRequest request) {
        scheduleTagService.updateScheduleTag(userId, projectId, scheduleTagId, request);
        Message message = new Message(StatusCode.OK, ResponseMessage.UPDATE_SCHEDULE_TAG);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @DeleteMapping("/{tagIndex}")
    public ResponseEntity<Message> deleteScheduleTag(@RequestHeader("userIndex") Long userId, @RequestHeader("projectIndex") Long projectId, @PathVariable("tagIndex") Long scheduleTagId) {
        scheduleTagService.deleteScheduleTag(userId, projectId, scheduleTagId);
        Message message = new Message(StatusCode.OK, ResponseMessage.DELETE_SCHEDULE_TAG);
        return new ResponseEntity<>(message, HttpStatus.OK);

    }
}
