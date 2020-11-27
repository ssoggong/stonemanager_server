package com.ssoggong.stonemanager_server.api;

import com.ssoggong.stonemanager_server.api.constants.Message;
import com.ssoggong.stonemanager_server.api.constants.ResponseMessage;
import com.ssoggong.stonemanager_server.dto.ProjectWithdrawResponse;
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

    @GetMapping
    public ResponseEntity<Message> readProjectList(@RequestHeader("userIndex") Long userIdx){
        ProjectWithdrawResponse response = userService.getProjectForWithdraw(userIdx);
        Message message = new Message(200, ResponseMessage.READ_PROJECT_LIST, response);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

//    @DeleteMapping("/{projectIndex}")
//    public ResponseEntity<Message> withdrawProject(){
//
//    }
}
