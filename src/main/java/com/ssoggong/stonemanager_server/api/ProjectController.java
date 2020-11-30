package com.ssoggong.stonemanager_server.api;

import com.ssoggong.stonemanager_server.api.constants.Message;
import com.ssoggong.stonemanager_server.api.constants.ResponseMessage;
import com.ssoggong.stonemanager_server.api.constants.StatusCode;
import com.ssoggong.stonemanager_server.dto.project.*;
import com.ssoggong.stonemanager_server.entity.Project;
import com.ssoggong.stonemanager_server.entity.Subject;
import com.ssoggong.stonemanager_server.entity.User;
import com.ssoggong.stonemanager_server.service.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/project")
public class ProjectController {
    private final UserService userService;
    private final ProjectService projectService;
    private final ProjectUserService projectUserService;
    private final SubjectService subjectService;


    @GetMapping
    public ResponseEntity<Message> readProjectList(@RequestHeader("userIndex") Long userId) {
        ProjectWithdrawResponse response = userService.getProjectForWithdraw(userId);
        Message message = new Message(StatusCode.OK, ResponseMessage.READ_PROJECT_LIST, response);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Message> createProject(@RequestHeader("userIndex") Long userId,
                                                 @RequestBody CreateProjectRequest projectRequest) {
        User user = userService.findById(userId);
        System.out.println("1");
        Subject subject = userService.findSubjectByUserAndSubjectName(userId, projectRequest.getSubjectName());
        System.out.println("2");
        for (Long memberId : projectRequest.getTeam()) {
            userService.findSubjectByUserAndSubjectName(memberId, projectRequest.getSubjectName());
        }
        projectService.createProject(projectRequest, subject, user);
        userService.inviteMember(projectRequest.getTeam());
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
        List<ProjectDetailResponse> result = new ArrayList<>();
        result.add(response);
        Message message = new Message(StatusCode.OK, ResponseMessage.READ_PROJECT, result);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/{projectIndex}/members")
    public ResponseEntity<Message> readProjectMember(@PathVariable Long projectIndex,
                                                     @RequestHeader("userIndex") Long userIndex) {
        ProjectMemberResponse response = projectService.readProjectMember(projectIndex, userIndex);
        Message message = new Message(StatusCode.OK, ResponseMessage.READ_PROJECT_USER, response);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/students")
    public ResponseEntity<Message> readStudent(@RequestHeader("userIndex") Long userId,
                                               @RequestHeader("subjectIndex") Long subjectId,
                                               @RequestBody String name){
        userService.findById(userId);
        Subject subject = userService.findSubjectByUserAndSubject(userId, subjectId);
        List<FindStudentDto> response = subjectService.findUser(subjectId, name);
        Message message = new Message(StatusCode.OK, ResponseMessage.READ_SUBJECT_STUDENT, response);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PutMapping("{projectIndex}")
    public ResponseEntity<Message> updateProject(@PathVariable Long projectIndex,
                                                 @RequestHeader("userIndex") Long userIndex,
                                                 @RequestBody UpdateProjectRequest request){
        userService.findById(userIndex);
        projectService.findByUserAndProject(userIndex, projectIndex);
        projectService.updateProject(projectIndex, request);
        userService.inviteMember(request.getTeam());
        Message message = new Message(StatusCode.OK, ResponseMessage.UPDATE_PROJECT);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PostMapping("/{projectIndex}/members/{memberUserIndex}")
    public ResponseEntity<Message> invitedMember(@PathVariable Long projectIndex,
                                                 @PathVariable Long memberUserIndex){
        User user = userService.findById(memberUserIndex);
        Project project = projectService.findById(projectIndex);
        projectUserService.createProjectUser(user, project);
        Message message = new Message(StatusCode.OK, ResponseMessage.CREATE_PROJECT_USER);
        System.out.println("Invitation Success");
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}

