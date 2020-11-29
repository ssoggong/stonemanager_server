package com.ssoggong.stonemanager_server.api;

import com.ssoggong.stonemanager_server.api.constants.Message;
import com.ssoggong.stonemanager_server.api.constants.ResponseMessage;
import com.ssoggong.stonemanager_server.api.constants.StatusCode;
import com.ssoggong.stonemanager_server.dto.checklist.ChecklistRequest;
import com.ssoggong.stonemanager_server.dto.comment.CommentRequest;
import com.ssoggong.stonemanager_server.entity.Project;
import com.ssoggong.stonemanager_server.entity.Task;
import com.ssoggong.stonemanager_server.entity.User;
import com.ssoggong.stonemanager_server.service.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/checklist")
public class ChecklistController {
    private final ChecklistService checklistService;
    private final UserService userService;
    private final ProjectService projectService;
    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<Message> createChecklist(@RequestHeader("userIndex") Long userId,
                                                 @RequestHeader("projectIndex") Long projectId,
                                                 @RequestHeader("taskIndex") Long taskId,
                                                 @RequestBody ChecklistRequest ChecklistRequest){
        User user = userService.findById(userId);
        Project project = projectService.findById(projectId);
        Task task = taskService.findById(taskId);
        checklistService.createChecklist(task, ChecklistRequest);
        Message message = new Message(StatusCode.OK, ResponseMessage.CREATE_CHECKLIST);
        return new ResponseEntity<>(message, HttpStatus.OK);

    }
}
