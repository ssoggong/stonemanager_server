package com.ssoggong.stonemanager_server.api;

import com.ssoggong.stonemanager_server.api.constants.Message;
import com.ssoggong.stonemanager_server.api.constants.ResponseMessage;
import com.ssoggong.stonemanager_server.api.constants.StatusCode;
import com.ssoggong.stonemanager_server.dto.project.ProjectDetailResponse;
import com.ssoggong.stonemanager_server.dto.project.ProjectWithdrawResponse;
import com.ssoggong.stonemanager_server.service.ProjectService;
import com.ssoggong.stonemanager_server.service.ProjectUserService;
import com.ssoggong.stonemanager_server.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/project")
public class ProjectController {
    private final UserService userService;
    private final ProjectService projectService;
    private final ProjectUserService projectUserService;

    @GetMapping
    public ResponseEntity<Message> readProjectList(@RequestHeader("userIndex") Long userIdx){
        ProjectWithdrawResponse response = userService.getProjectForWithdraw(userIdx);
        Message message = new Message(StatusCode.OK, ResponseMessage.READ_PROJECT_LIST, response);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @DeleteMapping("/{projectIndex}")
    public ResponseEntity<Message> withdrawProject(@PathVariable Long projectIndex,
                                                   @RequestHeader("userIndex") Long userIdx){
        projectUserService.deleteProjectUser(userIdx, projectIndex);
        Message message = new Message(StatusCode.OK, ResponseMessage.DELETE_PROJECT_USER);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/{projectIndex}")
    public ResponseEntity<Message> readProjectDetail(@PathVariable Long projectIndex,
                                                     @RequestHeader("userIndex") Long userIndex){
        ProjectDetailResponse response = projectService.getProjectDetail(projectIndex);
        Message message = new Message(StatusCode.OK, ResponseMessage.READ_PROJECT, response);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
