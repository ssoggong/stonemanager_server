package com.ssoggong.stonemanager_server.api;

import com.ssoggong.stonemanager_server.api.constants.Message;
import com.ssoggong.stonemanager_server.api.constants.ResponseMessage;
import com.ssoggong.stonemanager_server.api.constants.StatusCode;
import com.ssoggong.stonemanager_server.dto.tasktag.CreateTaskTagRequest;
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
                                                 @RequestBody CreateTaskTagRequest request){
        userService.findById(userId);
        Project project = projectService.findByUserAndProject(userId, projectId);
        taskService.findByProjectAndTask(project, taskId);
        taskTagService.createTaskTag(request, project);
        Message message = new Message(StatusCode.OK, ResponseMessage.CREATE_TASK_TAG);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
