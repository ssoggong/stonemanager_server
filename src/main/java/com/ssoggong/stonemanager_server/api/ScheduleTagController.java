package com.ssoggong.stonemanager_server.api;

import com.ssoggong.stonemanager_server.api.constants.Message;
import com.ssoggong.stonemanager_server.api.constants.ResponseMessage;
import com.ssoggong.stonemanager_server.api.constants.StatusCode;
import com.ssoggong.stonemanager_server.dto.CreateScheduleTagRequest;
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
}
