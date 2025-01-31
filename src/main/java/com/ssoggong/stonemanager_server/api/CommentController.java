package com.ssoggong.stonemanager_server.api;

import com.ssoggong.stonemanager_server.api.constants.Message;
import com.ssoggong.stonemanager_server.api.constants.ResponseMessage;
import com.ssoggong.stonemanager_server.api.constants.StatusCode;
import com.ssoggong.stonemanager_server.dto.comment.CommentRequest;
import com.ssoggong.stonemanager_server.dto.comment.CommentResponse;
import com.ssoggong.stonemanager_server.entity.Project;
import com.ssoggong.stonemanager_server.entity.Task;
import com.ssoggong.stonemanager_server.entity.User;
import com.ssoggong.stonemanager_server.service.CommentService;
import com.ssoggong.stonemanager_server.service.ProjectService;
import com.ssoggong.stonemanager_server.service.TaskService;
import com.ssoggong.stonemanager_server.service.UserService;
import io.micrometer.core.instrument.Measurement;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/comment")
public class CommentController {
    private final CommentService commentService;
    private final UserService userService;
    private final ProjectService projectService;
    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<Message> createComment(@RequestHeader("userIndex") Long userId,
                                                 @RequestHeader("projectIndex") Long projectId,
                                                 @RequestHeader("taskIndex") Long taskId,
                                                 @RequestBody CommentRequest commentRequest){
        User user = userService.findById(userId);
        Project project = projectService.findByUserAndProject(userId, projectId);
        Task task = taskService.findByProjectAndTask(project, taskId);
        commentService.createComment(commentRequest.getContent(), user, task);
        Message message = new Message(StatusCode.OK, ResponseMessage.CREATE_COMMENT);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PutMapping("/{commentIndex}")
    public ResponseEntity<Message> updateComment(@RequestHeader("userIndex") Long userId,
                                                 @RequestHeader("projectIndex") Long projectId,
                                                 @RequestHeader("taskIndex") Long taskId,
                                                 @PathVariable Long commentIndex,
                                                 @RequestBody CommentRequest commentRequest){
        userService.findById(userId);
        Project project = projectService.findByUserAndProject(userId, projectId);
        taskService.findByProjectAndTask(project, taskId);
        commentService.updateComment(commentIndex, commentRequest.getContent(), userId);
        Message message = new Message(StatusCode.OK, ResponseMessage.UPDATE_COMMENT);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @DeleteMapping("/{commentIndex}")
    public ResponseEntity<Message> deleteComment(@RequestHeader("userIndex") Long userId,
                                                 @RequestHeader("projectIndex") Long projectId,
                                                 @RequestHeader("taskIndex") Long taskId,
                                                 @PathVariable Long commentIndex){
        userService.findById(userId);
        Project project = projectService.findByUserAndProject(userId, projectId);
        taskService.findByProjectAndTask(project, taskId);
        commentService.deleteComment(commentIndex, userId);
        Message message = new Message(StatusCode.OK, ResponseMessage.DELETE_COMMENT);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Message> readComments(@RequestHeader("userIndex") Long userId,
                                                @RequestHeader("projectIndex") Long projectId,
                                                @RequestHeader("taskIndex") Long taskId){
        userService.findById(userId);
        Project project = projectService.findByUserAndProject(userId, projectId);
        Task task = taskService.findByProjectAndTask(project, taskId);
        CommentResponse response = commentService.readComments(task);
        Message message = new Message(StatusCode.OK, ResponseMessage.READ_COMMENT, response);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
