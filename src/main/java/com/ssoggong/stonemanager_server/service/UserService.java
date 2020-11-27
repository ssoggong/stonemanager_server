package com.ssoggong.stonemanager_server.service;

import com.ssoggong.stonemanager_server.dto.ProjectWithdrawDto;
import com.ssoggong.stonemanager_server.dto.ProjectWithdrawResponse;
import com.ssoggong.stonemanager_server.entity.Project;
import com.ssoggong.stonemanager_server.entity.ProjectUser;
import com.ssoggong.stonemanager_server.entity.User;
import com.ssoggong.stonemanager_server.exception.UserNotFountException;
import com.ssoggong.stonemanager_server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public void saveUser(User user) { userRepository.save(user); }

    public Optional<User> findById(Long userId) { return userRepository.findById(userId); }

    public ProjectWithdrawResponse getProjectForWithdraw(Long userId){
        User user = findById(userId).orElseThrow(() -> new UserNotFountException(userId));
        Set<ProjectUser> projectUserSet = user.getProjectUserSet();
        List<ProjectWithdrawDto> dto = new ArrayList<>();
        for(ProjectUser projectUser: projectUserSet) {
            dto.add(ProjectWithdrawDto.of(projectUser.getProject()));
        }
        return new ProjectWithdrawResponse(dto);
    }
}
