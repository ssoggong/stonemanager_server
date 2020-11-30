package com.ssoggong.stonemanager_server.api;

import com.ssoggong.stonemanager_server.api.constants.Message;
import com.ssoggong.stonemanager_server.api.constants.ResponseMessage;
import com.ssoggong.stonemanager_server.api.constants.StatusCode;
import com.ssoggong.stonemanager_server.dto.schedule.CreateScheduleRequest;
import com.ssoggong.stonemanager_server.dto.schedule.ReadScheduleDetailResponse;
import com.ssoggong.stonemanager_server.dto.schedule.ReadScheduleListResponse;
import com.ssoggong.stonemanager_server.dto.schedule.UpdateScheduleRequest;
import com.ssoggong.stonemanager_server.entity.Project;
import com.ssoggong.stonemanager_server.entity.Schedule;
import com.ssoggong.stonemanager_server.entity.User;
import com.ssoggong.stonemanager_server.service.ProjectService;
import com.ssoggong.stonemanager_server.service.ScheduleService;
import com.ssoggong.stonemanager_server.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/schedule")
public class ScheduleController {

    private final ScheduleService scheduleService;
    private final UserService userService;
    private final ProjectService projectService;

    @PostMapping
    public ResponseEntity<Message> createSchedule(@RequestHeader("userIndex") Long userId, @RequestHeader("projectIndex") Long projectId, @RequestBody CreateScheduleRequest request) {
        User user = userService.findById(userId);
        Project project = projectService.findById(projectId);
        scheduleService.createSchedule(project, request);
        Message message = new Message(StatusCode.OK, ResponseMessage.CREATE_SCHEDULE);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Message> readSchedule(@RequestHeader("userIndex") Long userId, @RequestHeader("projectIndex") Long projectId,
                                                @RequestParam("year") int year,
                                                @RequestParam(value = "month", required = false, defaultValue = "0")int month) {
        User user = userService.findById(userId);
        Project project = projectService.findById(projectId);
        ReadScheduleListResponse response;
        if(month != 0) {
            response = scheduleService.readSchedule(projectId, year, month);
        }
        else {
            response = scheduleService.readSchedule(projectId, year);
        }
        Message message = new Message(StatusCode.OK, ResponseMessage.READ_SCHEDULE_LIST, response);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Message> deleteSchedule(@RequestHeader("userIndex") Long userId, @RequestHeader("projectIndex") Long projectId, @RequestHeader("scheduleIndex") Long scheduleId) {
        User user = userService.findById(userId);
        Project project = projectService.findById(projectId);
        Schedule schedule = scheduleService.findById(scheduleId);
        scheduleService.deleteSchedule(scheduleId);
        Message message = new Message(StatusCode.OK, ResponseMessage.DELETE_SCHEDULE);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/{scheduleIndex}")
    public ResponseEntity<Message> readScheduleDetail(@RequestHeader("userIndex") Long userId, @RequestHeader("projectIndex") Long projectId, @PathVariable("scheduleIndex") Long scheduleId) {
        User user = userService.findById(userId);
        Project project = projectService.findById(projectId);
        Schedule schedule = scheduleService.findById(scheduleId);
        ReadScheduleDetailResponse response = scheduleService.readScheduleDetail(schedule);
        Message message = new Message(StatusCode.OK, ResponseMessage.READ_SCHEDULE_DETAIL, response);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PutMapping("/{scheduleIndex}")
    public ResponseEntity<Message> updateSchedule(@RequestHeader("userIndex") Long userId, @RequestHeader("projectIndex") Long projectId, @PathVariable("scheduleIndex") Long scheduleId, @RequestBody  UpdateScheduleRequest request) {
        User user = userService.findById(userId);
        Project project = projectService.findById(projectId);
        Schedule schedule = scheduleService.findById(scheduleId);
        scheduleService.updateSchedule(schedule, request);
        Message message = new Message(StatusCode.OK, ResponseMessage.UPDATE_SCHEDULE);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
