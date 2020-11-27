package com.ssoggong.stonemanager_server.api;

import com.ssoggong.stonemanager_server.api.constants.Message;
import com.ssoggong.stonemanager_server.api.constants.ResponseMessage;
import com.ssoggong.stonemanager_server.api.constants.StatusCode;
import com.ssoggong.stonemanager_server.dto.CreateScheduleRequest;
import com.ssoggong.stonemanager_server.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/schedule")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<Message> createSchedule(@RequestHeader("userIndex") Long userId, @RequestHeader("projectIndex") Long projectId, @RequestBody CreateScheduleRequest request) {
        scheduleService.createSchedule(userId, projectId, request);
        Message message = new Message(StatusCode.OK, ResponseMessage.CREATE_SCHEDULE);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
