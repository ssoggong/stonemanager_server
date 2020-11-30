package com.ssoggong.stonemanager_server.api;

import com.ssoggong.stonemanager_server.api.constants.Message;
import com.ssoggong.stonemanager_server.api.constants.ResponseMessage;
import com.ssoggong.stonemanager_server.api.constants.StatusCode;
import com.ssoggong.stonemanager_server.dto.file.AddFileRequest;
import com.ssoggong.stonemanager_server.entity.Project;
import com.ssoggong.stonemanager_server.entity.Task;
import com.ssoggong.stonemanager_server.service.FileService;
import com.ssoggong.stonemanager_server.service.ProjectService;
import com.ssoggong.stonemanager_server.service.TaskService;
import com.ssoggong.stonemanager_server.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/file")
public class FileController {
    private final FileService fileService;
    private final TaskService taskService;
    private final UserService userService;
    private final ProjectService projectService;

    @PostMapping
    public ResponseEntity<Message> postFile(@RequestHeader("userIndex") Long userId,
                                            @RequestHeader("projectIndex") Long projectId,
                                            @RequestHeader("taskIndex") Long taskId,
                                            @RequestBody AddFileRequest addFileRequest){
        userService.findById(userId);
        Project project = projectService.findByUserAndProject(userId, projectId);
        Task task = taskService.findByProjectAndTask(project, taskId);
        fileService.createFile(addFileRequest, task);
        Message message = new Message(StatusCode.OK, ResponseMessage.CREATE_FILE);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @DeleteMapping("/{fileIndex}")
    public ResponseEntity<Message> deleteFIle(@RequestHeader("userIndex") Long userId,
                                              @RequestHeader("projectIndex") Long projectId,
                                              @RequestHeader("taskIndex") Long taskId,
                                              @PathVariable Long fileIndex){
        userService.findById(userId);
        Project project = projectService.findByUserAndProject(userId, projectId);
        taskService.findByProjectAndTask(project, taskId);
        fileService.deleteFile(fileIndex);
        Message message = new Message(StatusCode.OK, ResponseMessage.DELETE_FILE);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
