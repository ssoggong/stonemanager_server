package com.ssoggong.stonemanager_server.api;

import com.ssoggong.stonemanager_server.api.constants.Message;
import com.ssoggong.stonemanager_server.api.constants.ResponseMessage;
import com.ssoggong.stonemanager_server.api.constants.StatusCode;
import com.ssoggong.stonemanager_server.dto.project.ReadProjectListResponse;
import com.ssoggong.stonemanager_server.entity.User;
import com.ssoggong.stonemanager_server.service.ProjectService;
import com.ssoggong.stonemanager_server.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/home")
@RequiredArgsConstructor
public class HomeController {

    private final UserService userService;
    private final ProjectService projectService;

    @GetMapping
    public ResponseEntity<Message> readProjectList(@RequestHeader("userIndex") Long userId) {
        User user = userService.findById(userId);
        ReadProjectListResponse response = userService.ReadProjectList(user);
        Message message = new Message(StatusCode.OK, ResponseMessage.READ_PROJECT_LIST, response);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}