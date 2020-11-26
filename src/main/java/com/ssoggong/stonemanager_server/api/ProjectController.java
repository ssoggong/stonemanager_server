package com.ssoggong.stonemanager_server.api;

import com.ssoggong.stonemanager_server.api.constants.Message;
import com.ssoggong.stonemanager_server.api.constants.ResponseMessage;
import com.ssoggong.stonemanager_server.api.constants.StatusCode;
import com.ssoggong.stonemanager_server.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping("/home")
    public ResponseEntity<Message> readProjectList(@RequestHeader("userIndex") Long userId) {

        Message message = new Message();
        message.setStatus(StatusCode.OK);
        message.setMessage(ResponseMessage.READ_PROJECT_LIST);

        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
