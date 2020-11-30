package com.ssoggong.stonemanager_server.api;

import com.ssoggong.stonemanager_server.api.constants.Message;
import com.ssoggong.stonemanager_server.api.constants.ResponseMessage;
import com.ssoggong.stonemanager_server.api.constants.StatusCode;
import com.ssoggong.stonemanager_server.dto.user.*;
import com.ssoggong.stonemanager_server.service.AuthService;
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
    private final AuthService authService;
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

    @PostMapping("/register")
    public ResponseEntity<Message> registerUser(@RequestBody RegisterUserRequest request){
        UserResponse response = userService.createUser(request);
        Message message = new Message(StatusCode.OK, ResponseMessage.CREATE_USER, response);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Message> userLogin(@RequestBody LoginRequest request){
        UserResponse response = userService.userLogin(request);
        Message message = new Message(StatusCode.OK, ResponseMessage.LOGIN, response);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PutMapping("/password")
    public ResponseEntity<Message> updatePassword(@RequestHeader("userIndex") Long userId,
                                                  @RequestBody UpdatePasswordRequest request){
        userService.updatePassword(request, userId);
        Message message = new Message(StatusCode.OK, ResponseMessage.UPDATE_PASSWORD);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PostMapping("/auth/email")
    public ResponseEntity<Message> sendAuthCode(@RequestBody EmailAuthRequest request){
        authService.sendAuthcode(request);
        Message message = new Message(StatusCode.OK, ResponseMessage.SEND_AUTH_CODE);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PostMapping("/auth")
    public ResponseEntity<Message> checkAuthCode(@RequestBody AuthRequest request){
        authService.checkAuthcode(request);
        Message message = new Message(StatusCode.OK, ResponseMessage.CHECK_AUTH_CODE);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PutMapping("/password/find")
    public ResponseEntity<Message> findPassword(@RequestHeader("userIndex") Long userId,
                                                @RequestBody FindPasswordRequest request){
        userService.findPassword(request, userId);
        Message message = new Message(StatusCode.OK, ResponseMessage.FIND_PASSWORD);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
