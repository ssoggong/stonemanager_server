package com.ssoggong.stonemanager_server.api;

import com.ssoggong.stonemanager_server.api.constants.Message;
import com.ssoggong.stonemanager_server.api.constants.ResponseMessage;
import com.ssoggong.stonemanager_server.api.constants.StatusCode;
import com.ssoggong.stonemanager_server.dto.CreateTaskResponse;
import com.ssoggong.stonemanager_server.dto.task.UpdateTaskRequest;
import com.ssoggong.stonemanager_server.entity.Checklist;
import com.ssoggong.stonemanager_server.exception.ChecklistNotFoundException;
import com.ssoggong.stonemanager_server.repository.ChecklistRepository;
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
    private final ChecklistRepository checklistRepository;

    @PostMapping
    public ResponseEntity<Message> createTask(@RequestHeader("userIndex") Long userId, @RequestHeader("projectIndex") Long projectId)  {
        CreateTaskResponse response = taskService.createTask(userId, projectId);
        Message message = new Message(StatusCode.OK, ResponseMessage.CREATE_TASK, response);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PutMapping("{taskIndex}")
    public ResponseEntity<Message> updateTask(@RequestHeader("userIndex") Long userId, @RequestHeader("projectIndex") Long projectId, @PathVariable("taskIndex") Long taskId, @RequestBody UpdateTaskRequest request) {
        Checklist checklist = checklistRepository.findById(26L).orElseThrow(() -> new ChecklistNotFoundException(26L));
        taskService.updateTask(userId, projectId, taskId, request);
        Message message = new Message(StatusCode.OK, ResponseMessage.UPDATE_TASK);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
