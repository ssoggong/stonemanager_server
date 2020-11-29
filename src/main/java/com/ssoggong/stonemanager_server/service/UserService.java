package com.ssoggong.stonemanager_server.service;

import com.ssoggong.stonemanager_server.dto.project.ReadProjectListDto;
import com.ssoggong.stonemanager_server.dto.project.ReadProjectListResponse;
import com.ssoggong.stonemanager_server.dto.project.ProjectWithdrawDto;
import com.ssoggong.stonemanager_server.dto.project.ProjectWithdrawResponse;
import com.ssoggong.stonemanager_server.dto.user.UserInfoResponse;
import com.ssoggong.stonemanager_server.dto.user.UserSubjectDto;
import com.ssoggong.stonemanager_server.dto.user.UserSubjectResponse;
import com.ssoggong.stonemanager_server.entity.Project;
import com.ssoggong.stonemanager_server.entity.ProjectUser;
import com.ssoggong.stonemanager_server.entity.User;
import com.ssoggong.stonemanager_server.entity.UserSubject;
import com.ssoggong.stonemanager_server.exception.UserNotFoundException;
import com.ssoggong.stonemanager_server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.List;
import java.util.Set;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ProjectService projectService;

    @Transactional
    public void saveUser(User user) { userRepository.save(user); }

    public User findById(Long userId) { return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId)); }

    public ReadProjectListResponse ReadProjectList(Long userId) {
        User user = findById(userId);
        Set<ProjectUser> projectUserSet = user.getProjectUserSet();
        List<ReadProjectListDto> dto = new ArrayList<>();
        for(ProjectUser projectUser: projectUserSet) {
            Project project = projectUser.getProject();
            dto.add(ReadProjectListDto.of(project, projectService.getProgressRate(project)));
        }
        return new ReadProjectListResponse(dto);
    }

    public ProjectWithdrawResponse getProjectForWithdraw(Long userId) {
        User user = findById(userId);
        Set<ProjectUser> projectUserSet = user.getProjectUserSet();
        List<ProjectWithdrawDto> dto = new ArrayList<>();
        for (ProjectUser projectUser : projectUserSet) {
            dto.add(ProjectWithdrawDto.of(projectUser.getProject()));
        }
        return new ProjectWithdrawResponse(dto);
    }

    public UserInfoResponse readUserInfo(Long userId){
        User user = findById(userId);
        return new UserInfoResponse(user.getName(), user.getStudentId(), user.getEmail(), user.getEmail());
    }

    public UserSubjectResponse readUserSubject(Long userId){
        User user = findById(userId);
        List<UserSubjectDto> dtos = new ArrayList<>();
        for(UserSubject userSubject : user.getUserSubjectSet()){
            if(userSubject.getUser().getIdx() == userId){
                dtos.add(UserSubjectDto.of(userSubject.getSubject()));
            }
        }
        return new UserSubjectResponse(dtos);
    }
}
