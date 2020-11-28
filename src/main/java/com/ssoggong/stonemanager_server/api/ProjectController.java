package com.ssoggong.stonemanager_server.api;

import com.ssoggong.stonemanager_server.api.constants.Message;
import com.ssoggong.stonemanager_server.api.constants.ResponseMessage;
import com.ssoggong.stonemanager_server.api.constants.StatusCode;
import com.ssoggong.stonemanager_server.dto.project.CreateProjectRequest;
import com.ssoggong.stonemanager_server.dto.project.ProjectDetailResponse;
import com.ssoggong.stonemanager_server.dto.project.ProjectMemberResponse;
import com.ssoggong.stonemanager_server.dto.project.ProjectWithdrawResponse;
import com.ssoggong.stonemanager_server.entity.Subject;
import com.ssoggong.stonemanager_server.entity.User;
import com.ssoggong.stonemanager_server.service.ProjectService;
import com.ssoggong.stonemanager_server.service.ProjectUserService;
import com.ssoggong.stonemanager_server.service.UserService;
import com.ssoggong.stonemanager_server.service.UserSubjectService;
import lombok.AllArgsConstructor;
import lombok.Getter;
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
    private final UserSubjectService userSubjectService;

    @GetMapping
    public ResponseEntity<Message> readProjectList(@RequestHeader("userIndex") Long userId) {
        ProjectWithdrawResponse response = userService.getProjectForWithdraw(userId);
        Message message = new Message(StatusCode.OK, ResponseMessage.READ_PROJECT_LIST, response);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    // TODO 이메일 전송
    @PostMapping
    public ResponseEntity<Message> createProject(@RequestHeader("userIndex") Long userId,
                                                 @RequestBody CreateProjectRequest projectRequest) {
        userService.findById(userId);
        Subject subject = userService.findSubjectByUserAndSubject(userId, projectRequest.getSubjectId());
        for (Long memberId : projectRequest.getTeam()) {
            userService.findSubjectByUserAndSubject(memberId, projectRequest.getSubjectId());
        }
        projectService.createProject(projectRequest, subject);
        Message message = new Message(StatusCode.OK, ResponseMessage.CREATE_PROJECT);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @DeleteMapping("/{projectIndex}")
    public ResponseEntity<Message> withdrawProject(@PathVariable Long projectIndex,
                                                   @RequestHeader("userIndex") Long userIdx) {
        projectUserService.deleteProjectUser(userIdx, projectIndex);
        Message message = new Message(StatusCode.OK, ResponseMessage.DELETE_PROJECT_USER);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/{projectIndex}")
    public ResponseEntity<Message> readProjectDetail(@PathVariable Long projectIndex,
                                                     @RequestHeader("userIndex") Long userIndex) {
        ProjectDetailResponse response = projectService.getProjectDetail(projectIndex, userIndex);
        Message message = new Message(StatusCode.OK, ResponseMessage.READ_PROJECT, response);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/{projectIndex}/members")
    public ResponseEntity<Message> readProjectMember(@PathVariable Long projectIndex,
                                                     @RequestHeader("userIndex") Long userIndex) {
        ProjectMemberResponse response = projectService.readProjectMember(projectIndex, userIndex);
        Message message = new Message(StatusCode.OK, ResponseMessage.READ_PROJECT_USER, response);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}

