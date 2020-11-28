package com.ssoggong.stonemanager_server.exception;

import com.ssoggong.stonemanager_server.api.constants.Message;
import com.ssoggong.stonemanager_server.api.constants.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UnauthorizedExceptionController {
    @ExceptionHandler({
            UnauthorizedUserException.class
    })
    public ResponseEntity<Message> BadRequestException(final UnauthorizedException exception){
        Message message = new Message(400, ResponseMessage.UNAUTHORIZED_ACCESS);
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }
}
