package com.ssoggong.stonemanager_server.api;

import com.ssoggong.stonemanager_server.api.constants.Message;
import com.ssoggong.stonemanager_server.api.constants.ResponseMessage;
import com.ssoggong.stonemanager_server.api.constants.StatusCode;
import com.ssoggong.stonemanager_server.dto.task.CreateTaskResponse;
import com.ssoggong.stonemanager_server.dto.task.ReadTaskDetailDto;
import com.ssoggong.stonemanager_server.dto.task.ReadTaskListResponse;
import com.ssoggong.stonemanager_server.dto.task.UpdateTaskRequest;
import com.ssoggong.stonemanager_server.entity.*;
import com.ssoggong.stonemanager_server.repository.ChecklistRepository;
import com.ssoggong.stonemanager_server.service.ProjectService;
import com.ssoggong.stonemanager_server.service.TaskService;
import com.ssoggong.stonemanager_server.service.TaskTagService;
import com.ssoggong.stonemanager_server.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/task")
public class TaskController {

    private final TaskService taskService;
    private final UserService userService;
    private final ProjectService projectService;
    private final TaskTagService taskTagService;

    @PostMapping
    public ResponseEntity<Message> createTask(@RequestHeader("userIndex") Long userId, @RequestHeader("projectIndex") Long projectId)  {
        User user = userService.findById(userId);
        Project project = projectService.findById(projectId);
        CreateTaskResponse response = taskService.createTask(project);
        Message message = new Message(StatusCode.OK, ResponseMessage.CREATE_TASK, response);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PutMapping("/{taskIndex}")
    public ResponseEntity<Message> updateTask(@RequestHeader("userIndex") Long userId, @RequestHeader("projectIndex") Long projectId, @PathVariable("taskIndex") Long taskId, @RequestBody UpdateTaskRequest request) {
        User user = userService.findById(userId);
        Project project = projectService.findById(projectId);
        Task task = taskService.findById(taskId);
        taskService.updateTask(task, request);
        Message message = new Message(StatusCode.OK, ResponseMessage.UPDATE_TASK);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Message> deleteTask(@RequestHeader("userIndex") Long userId, @RequestHeader("projectIndex") Long projectId, @RequestHeader("taskIndex") Long taskId) {
        User user = userService.findById(userId);
        Project project = projectService.findById(projectId);
        Task task = taskService.findById(taskId);
        taskService.deleteTask(task);
        Message message = new Message(StatusCode.OK, ResponseMessage.DELETE_TASK);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<Message> readTaskList(@RequestHeader("userIndex") Long userId, @RequestHeader("projectIndex") Long projectId,
                                                      @RequestParam(value = "assigneeIndex", required = false, defaultValue = "0") Long assigneeId,
                                                      @RequestParam(value = "tagIndex", required = false, defaultValue = "0")Long tagId) {

        User user = userService.findById(userId);
        Project project = projectService.findById(projectId);

        ReadTaskListResponse response;
        if(assigneeId == 0 && tagId == 0) {
            response = taskService.readTaskList(project); }
        else {
            if(assigneeId != 0) {
                User assignee = userService.findById(assigneeId);
                response = taskService.readTaskListByUser(assignee);
            }
            else {
                TaskTag taskTag = taskTagService.findById(tagId);
                response = taskService.readTaskListByTag(taskTag);
            }
        }

        Message message = new Message(StatusCode.OK, ResponseMessage.READ_TASK_LIST, response);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/{taskIndex}")
    public ResponseEntity<Message> readTaskListDetail(@RequestHeader("userIndex") Long userId, @RequestHeader("projectIndex") Long projectId, @PathVariable("taskIndex") Long taskId) {
        User user = userService.findById(userId);
        Project project = projectService.findById(projectId);
        Task task = taskService.findById(taskId);
        ReadTaskDetailDto response = taskService.readTaskDetail(task);
        Message message = new Message(StatusCode.OK, ResponseMessage.READ_TASK, response);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
