package com.ssoggong.stonemanager_server.api;

import com.ssoggong.stonemanager_server.api.constants.Message;
import com.ssoggong.stonemanager_server.api.constants.ResponseMessage;
import com.ssoggong.stonemanager_server.api.constants.StatusCode;
import com.ssoggong.stonemanager_server.dto.tag.TagRequest;
import com.ssoggong.stonemanager_server.dto.tag.TagResponse;
import com.ssoggong.stonemanager_server.entity.Project;
import com.ssoggong.stonemanager_server.service.ProjectService;
import com.ssoggong.stonemanager_server.service.TaskService;
import com.ssoggong.stonemanager_server.service.TaskTagService;
import com.ssoggong.stonemanager_server.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/tasktag")
public class TaskTagController {
    private final TaskTagService taskTagService;
    private final UserService userService;
    private final ProjectService projectService;
    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<Message> createTaskTag(@RequestHeader("userIndex") Long userId,
                                                 @RequestHeader("projectIndex") Long projectId,
                                                 @RequestHeader("taskIndex") Long taskId,
                                                 @RequestBody TagRequest request){
        userService.findById(userId);
        Project project = projectService.findByUserAndProject(userId, projectId);
        taskService.findByProjectAndTask(project, taskId);
        taskTagService.createTaskTag(request, project);
        Message message = new Message(StatusCode.OK, ResponseMessage.CREATE_TASK_TAG);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PutMapping("/{tagIndex}")
    public ResponseEntity<Message> updateTaskTag(@PathVariable Long tagIndex,
                                                 @RequestHeader("userIndex") Long userId,
                                                 @RequestHeader("projectIndex") Long projectId,
                                                 @RequestHeader("taskIndex") Long taskId,
                                                 @RequestBody TagRequest request){
        userService.findById(userId);
        Project project = projectService.findByUserAndProject(userId, projectId);
        taskService.findByProjectAndTask(project, taskId);
        taskTagService.updateTaskTag(request, tagIndex);
        Message message = new Message(StatusCode.OK, ResponseMessage.CREATE_TASK_TAG);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @DeleteMapping("/{tagIndex}")
    public ResponseEntity<Message> deleteTaskTag(@PathVariable Long tagIndex,
                                                 @RequestHeader("userIndex") Long userId,
                                                 @RequestHeader("projectIndex") Long projectId,
                                                 @RequestHeader("taskIndex") Long taskId){
        userService.findById(userId);
        Project project = projectService.findByUserAndProject(userId, projectId);
        taskService.findByProjectAndTask(project, taskId);
        taskTagService.deleteTaskTag(tagIndex);
        Message message = new Message(StatusCode.OK, ResponseMessage.CREATE_TASK_TAG);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Message> readTaskTags(@RequestHeader("userIndex") Long userId,
                                                @RequestHeader("projectIndex") Long projectId){
        userService.findById(userId);
        projectService.findByUserAndProject(userId, projectId);
        TagResponse response = projectService.readTaskTags(projectId);
        Message message = new Message(StatusCode.OK, ResponseMessage.DELETE_TASK_TAG, response);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
