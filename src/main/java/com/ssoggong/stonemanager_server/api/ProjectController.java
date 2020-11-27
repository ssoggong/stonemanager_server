package com.ssoggong.stonemanager_server.api;

import com.ssoggong.stonemanager_server.api.constants.Message;
import com.ssoggong.stonemanager_server.api.constants.ResponseMessage;
import com.ssoggong.stonemanager_server.api.constants.StatusCode;
import com.ssoggong.stonemanager_server.dto.ProjectWithdrawResponse;
import com.ssoggong.stonemanager_server.service.ProjectService;
import com.ssoggong.stonemanager_server.service.ProjectUserService;
import com.ssoggong.stonemanager_server.service.UserService;
import io.micrometer.core.instrument.Measurement;
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
    public ResponseEntity<Message> withdrawProject(@RequestHeader("userIndex") Long userIdx,
                                                   @PathVariable Long projectIndex){
        projectUserService.deleteProjectUser(userIdx, projectIndex);
        Message message = new Message(StatusCode.OK, ResponseMessage.DELETE_PROJECT_USER);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
