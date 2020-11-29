package com.ssoggong.stonemanager_server.service;

import com.ssoggong.stonemanager_server.dto.project.ReadProjectListDto;
import com.ssoggong.stonemanager_server.dto.project.ReadProjectListResponse;
import com.ssoggong.stonemanager_server.dto.project.ProjectWithdrawDto;
import com.ssoggong.stonemanager_server.dto.project.ProjectWithdrawResponse;
import com.ssoggong.stonemanager_server.dto.user.*;
import com.ssoggong.stonemanager_server.entity.Project;
import com.ssoggong.stonemanager_server.entity.ProjectUser;
import com.ssoggong.stonemanager_server.entity.Subject;
import com.ssoggong.stonemanager_server.entity.User;
import com.ssoggong.stonemanager_server.entity.UserSubject;
import com.ssoggong.stonemanager_server.exception.MultipleNotFoundException;
import com.ssoggong.stonemanager_server.exception.UserNotFoundException;
import com.ssoggong.stonemanager_server.exception.WrongPasswordException;
import com.ssoggong.stonemanager_server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ProjectService projectService;

    @Transactional
    public void saveUser(User user) { userRepository.save(user); }

    public User findById(Long userId) { return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId)); }

    public Subject findSubjectByUserAndSubject(Long userId, Long subjectId){
        User user = findById(userId);
        System.out.println(user.getUserSubjectSet().size());
        List<UserSubject> userSubjects = user.getUserSubjectSet().stream()
                .filter(userSubject -> userSubject.getSubject().getIdx() == subjectId)
                .collect(Collectors.toList());
        if(userSubjects.size() != 1) throw new MultipleNotFoundException();
        return userSubjects.get(0).getSubject();
    }

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

    @Transactional
    public void updateProfileImage(Long userId, String imageUri){
        User user = findById(userId);
        user.setImage(imageUri);
        userRepository.save(user);
    }

    @Transactional
    public UserResponse createUser(RegisterUserRequest request){
        User user = User.builder()
                .name(request.getUserName())
                .studentId(request.getStudentId())
                .pw(request.getPassword())
                .email(request.getEmail())
                .image(null)
                .salt(null)
                .build();
        saveUser(user);
        return new UserResponse(user.getIdx());
    }

    @Transactional
    public UserResponse userLogin(LoginRequest request){
        User user = userRepository.findByStudentId(request.getUserId()).orElseThrow(() -> new UserNotFoundException(-1L));
        if(user.getPw() != request.getPassword()) throw new WrongPasswordException(request.getPassword());
        return new UserResponse(user.getIdx());
    }

    @Transactional
    public void updatePassword(UpdatePasswordRequest request, Long userId){
        User user = findById(userId);
        if(user.getPw() != request.getPassword()) throw new WrongPasswordException(request.getPassword());
        user.setPw(request.getNewPassword());
        saveUser(user);
    }
}
