package com.ssoggong.stonemanager_server.api;

import com.ssoggong.stonemanager_server.api.constants.Message;
import com.ssoggong.stonemanager_server.api.constants.ResponseMessage;
import com.ssoggong.stonemanager_server.api.constants.StatusCode;
import com.ssoggong.stonemanager_server.dto.CreateTaskResponse;
import com.ssoggong.stonemanager_server.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/task")
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<Message> createTask(@RequestHeader("userIndex") Long userId, @RequestHeader("projectIndex") Long projectId)  {
        CreateTaskResponse response = taskService.createTask(userId, projectId);
        Message message = new Message(StatusCode.OK, ResponseMessage.CREATE_TASK, response);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
