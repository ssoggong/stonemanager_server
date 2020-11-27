package com.ssoggong.stonemanager_server.exception;

import com.ssoggong.stonemanager_server.api.constants.Message;
import com.ssoggong.stonemanager_server.api.constants.ResponseMessage;
import com.ssoggong.stonemanager_server.api.constants.StatusCode;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler({
            UserNotFoundException.class,
            ProjectNotFoundException.class,
            NotFoundException.class,
            ScheduleTagNotFoundException.class,
            ScheduleNotFoundException.class
    })
    public HttpEntity<Message> BadRequestException(final RuntimeException exception){
        Message message = new Message(400, ResponseMessage.NOT_FOUNT_VALUE);
        return new HttpEntity<>(message);
    }
}
