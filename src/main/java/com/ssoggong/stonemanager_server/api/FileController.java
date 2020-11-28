package com.ssoggong.stonemanager_server.api;

import com.ssoggong.stonemanager_server.api.constants.Message;
import com.ssoggong.stonemanager_server.api.constants.ResponseMessage;
import com.ssoggong.stonemanager_server.api.constants.StatusCode;
import com.ssoggong.stonemanager_server.dto.file.AddFileRequest;
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
        // TODO 해당 유저가 프로젝트의 멤버인지 검증
        projectService.findById(projectId);
        // TODO 해당 프로젝트와 태스크가 연결되었는지
        fileService.createFile(addFileRequest, taskService.findById(taskId));
        Message message = new Message(StatusCode.OK, ResponseMessage.CREATE_FILE);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @DeleteMapping("/{fileIndex}")
    public ResponseEntity<Message> deleteFIle(@RequestHeader("userIndex") Long userId,
                                              @RequestHeader("projectIndex") Long projectId,
                                              @RequestHeader("taskIndex") Long taskId,
                                              @PathVariable Long fileIndex){
        userService.findById(userId);
        projectService.findById(projectId);
        taskService.findById(taskId);
        fileService.deleteFile(fileIndex);
        Message message = new Message(StatusCode.OK, ResponseMessage.DELETE_FILE);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
