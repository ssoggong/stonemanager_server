package com.ssoggong.stonemanager_server.api;

import com.ssoggong.stonemanager_server.api.constants.Message;
import com.ssoggong.stonemanager_server.api.constants.ResponseMessage;
import com.ssoggong.stonemanager_server.api.constants.StatusCode;
import com.ssoggong.stonemanager_server.entity.Project;
import com.ssoggong.stonemanager_server.entity.ProjectUser;
import com.ssoggong.stonemanager_server.entity.User;
import com.ssoggong.stonemanager_server.service.ProjectService;
import com.ssoggong.stonemanager_server.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/home")
@RequiredArgsConstructor
public class HomeController {

    private final UserService userService;
    private final ProjectService projectService;

    ModelMapper modelMapper = new ModelMapper();

    @GetMapping
    public ResponseEntity<Message> readProjectList(@RequestHeader("userIndex") Long userId) {

        Message message = new Message();
        List<ReadProjectListDto> collect;
        try{
            collect = userService.ReadProjectList(userId);
        }
        catch (IllegalArgumentException i) {
            message.setStatus(StatusCode.BAD_REQUEST);
            message.setMessage(ResponseMessage.NOT_FOUND_USER);
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }

        message.setStatus(StatusCode.OK);
        message.setMessage(ResponseMessage.READ_PROJECT_LIST);
        message.setData(new Result(collect));
        return new ResponseEntity<>(message, HttpStatus.OK);
    }


    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T projectInfo;
    }

    @Data
    @AllArgsConstructor
    public static
    class ReadProjectListDto {
        private Long projectIndex;
        private String projectName;
        private String projectSubject;
        private Integer projectProgress;
    }

}