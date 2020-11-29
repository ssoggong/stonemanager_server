package com.ssoggong.stonemanager_server.api;

import com.ssoggong.stonemanager_server.api.constants.Message;
import com.ssoggong.stonemanager_server.api.constants.ResponseMessage;
import com.ssoggong.stonemanager_server.api.constants.StatusCode;
import com.ssoggong.stonemanager_server.dto.user.UserProfileDto;
import com.ssoggong.stonemanager_server.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    @GetMapping
    public ResponseEntity<Message> readUserInfo(@RequestHeader("userIndex") Long userId){
        Message message = new Message(StatusCode.OK, ResponseMessage.READ_USER_INFO, userService.readUserInfo(userId));
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/subject")
    public ResponseEntity<Message> readUserSubject(@RequestHeader("userIndex") Long userId){
        Message message = new Message(StatusCode.OK, ResponseMessage.READ_USER_SUBJECT, userService.readUserSubject(userId));
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/profile")
    public ResponseEntity<Message> updateProfileImage(@RequestHeader("userIndex") Long userId,
                                                      @RequestBody UserProfileDto userProfile){
        userService.updateProfileImage(userId, userProfile.getUserProfileImage());
        Message message = new Message(StatusCode.OK, ResponseMessage.UPDATE_USER_IMAGE);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
