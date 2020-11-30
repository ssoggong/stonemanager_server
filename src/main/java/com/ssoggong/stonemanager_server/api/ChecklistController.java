package com.ssoggong.stonemanager_server.api;

import com.ssoggong.stonemanager_server.api.constants.Message;
import com.ssoggong.stonemanager_server.api.constants.ResponseMessage;
import com.ssoggong.stonemanager_server.api.constants.StatusCode;
import com.ssoggong.stonemanager_server.dto.checklist.ChecklistDto;
import com.ssoggong.stonemanager_server.dto.checklist.ChecklistRequest;
import com.ssoggong.stonemanager_server.dto.checklist.ChecklistResponse;
import com.ssoggong.stonemanager_server.dto.comment.CommentRequest;
import com.ssoggong.stonemanager_server.entity.Checklist;
import com.ssoggong.stonemanager_server.entity.Project;
import com.ssoggong.stonemanager_server.entity.Task;
import com.ssoggong.stonemanager_server.entity.User;
import com.ssoggong.stonemanager_server.service.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        ChecklistResponse response = checklistService.createChecklist(task, ChecklistRequest);
        Message message = new Message(StatusCode.OK, ResponseMessage.CREATE_CHECKLIST, response);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PutMapping("/{checklistIndex}")
    public ResponseEntity<Message> updateChecklist(@RequestHeader("userIndex") Long userId,
                                                   @RequestHeader("projectIndex") Long projectId,
                                                   @RequestHeader("taskIndex") Long taskId,
                                                   @PathVariable("checklistIndex") Long checklistId,
                                                   @RequestBody ChecklistRequest ChecklistRequest) {
        User user = userService.findById(userId);
        Project project = projectService.findById(projectId);
        Task task = taskService.findById(taskId);
        Checklist checklist = checklistService.findById(checklistId);
        checklistService.updateChecklist(checklist, ChecklistRequest);
        Message message = new Message(StatusCode.OK, ResponseMessage.UPDATE_CHECKLIST);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Message> deleteChecklist(@RequestHeader("userIndex") Long userId,
                                                   @RequestHeader("projectIndex") Long projectId,
                                                   @RequestHeader("taskIndex") Long taskId,
                                                   @RequestHeader("checklistIndex") Long checklistId) {
        User user = userService.findById(userId);
        Project project = projectService.findById(projectId);
        Task task = taskService.findById(taskId);
        Checklist checklist = checklistService.findById(checklistId);
        checklistService.deleteChecklist(checklist);
        Message message = new Message(StatusCode.OK, ResponseMessage.DELETE_CHECKLIST);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Message> readChecklist(@RequestHeader("userIndex") Long userId,
                                                 @RequestHeader("projectIndex") Long projectId,
                                                 @RequestHeader("taskIndex") Long taskId) {
        User user = userService.findById(userId);
        Project project = projectService.findById(projectId);
        Task task = taskService.findById(taskId);
        List<ChecklistDto> response = checklistService.readChecklist(task);
        Message message = new Message(StatusCode.OK, ResponseMessage.READ_CHECKLIST, response);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
