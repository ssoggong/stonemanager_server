package com.ssoggong.stonemanager_server.api;

import com.ssoggong.stonemanager_server.api.constants.Message;
import com.ssoggong.stonemanager_server.api.constants.ResponseMessage;
import com.ssoggong.stonemanager_server.api.constants.StatusCode;
import com.ssoggong.stonemanager_server.dto.CreateScheduleRequest;
import com.ssoggong.stonemanager_server.dto.ReadScheduleListResponse;
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

    @GetMapping
    public ResponseEntity<Message> readSchedule(@RequestHeader("userIndex") Long userId, @RequestHeader("projectIndex") Long projectId, @RequestParam("year") int year, @RequestParam int month) {
        ReadScheduleListResponse response = scheduleService.readSchedule(userId, projectId, year, month);
        Message message = new Message(StatusCode.OK, ResponseMessage.READ_SCHEDULE_LIST, response);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Message> deleteSchedule(@RequestHeader("userIndex") Long userId, @RequestHeader("projectIndex") Long projectId, @RequestHeader("scheduleIndex") Long scheduleId) {
        scheduleService.deleteSchedule(userId, projectId, scheduleId);
        Message message = new Message(StatusCode.OK, ResponseMessage.DELETE_SCHEDULE);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
