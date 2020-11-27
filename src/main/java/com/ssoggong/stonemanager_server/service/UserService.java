package com.ssoggong.stonemanager_server.service;

import com.ssoggong.stonemanager_server.dto.ReadProjectListDto;
import com.ssoggong.stonemanager_server.dto.ReadProjectListResponse;
import com.ssoggong.stonemanager_server.dto.project.ProjectWithdrawDto;
import com.ssoggong.stonemanager_server.dto.project.ProjectWithdrawResponse;
import com.ssoggong.stonemanager_server.entity.ProjectUser;
import com.ssoggong.stonemanager_server.entity.User;
import com.ssoggong.stonemanager_server.exception.UserNotFoundException;
import com.ssoggong.stonemanager_server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public void saveUser(User user) { userRepository.save(user); }

    public Optional<User> findById(Long userId) { return userRepository.findById(userId); }

    public ReadProjectListResponse ReadProjectList(Long userId) {
        User user = findById(userId).orElseThrow(UserNotFoundException::new);
        Set<ProjectUser> projectUserSet = user.getProjectUserSet();
        List<ReadProjectListDto> dto = new ArrayList<>();
        for(ProjectUser projectUser: projectUserSet) {
            dto.add(ReadProjectListDto.of(projectUser.getProject()));
        }
        return new ReadProjectListResponse(dto);
    }

    public ProjectWithdrawResponse getProjectForWithdraw(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        Set<ProjectUser> projectUserSet = user.getProjectUserSet();
        List<ProjectWithdrawDto> dto = new ArrayList<>();
        for (ProjectUser projectUser : projectUserSet) {
            dto.add(ProjectWithdrawDto.of(projectUser.getProject()));
        }
        return new ProjectWithdrawResponse(dto);
    }
}
