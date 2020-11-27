package com.ssoggong.stonemanager_server.service;

import com.ssoggong.stonemanager_server.dto.ProjectWithdrawDto;
import com.ssoggong.stonemanager_server.dto.ProjectWithdrawResponse;
import com.ssoggong.stonemanager_server.api.HomeController;
import com.ssoggong.stonemanager_server.entity.Project;
import com.ssoggong.stonemanager_server.entity.ProjectUser;
import com.ssoggong.stonemanager_server.entity.User;
import com.ssoggong.stonemanager_server.exception.UserNotFoundException;
import com.ssoggong.stonemanager_server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public void saveUser(User user) { userRepository.save(user); }

    public Optional<User> findById(Long userId) { return userRepository.findById(userId); }

    public List<HomeController.ReadProjectListDto> ReadProjectList(Long userId) {

        User user = findById(userId).orElse(null);

        if(user == null) {
            throw new IllegalArgumentException();
        }

        Set<ProjectUser> projectUserSet = user.getProjectUserSet();
        Set<Project> projectSet = new HashSet<>();
        for(ProjectUser projectUser: projectUserSet) {
            projectSet.add(projectUser.getProject());
        }

        //엔티티 -> DTO 변환
        List<HomeController.ReadProjectListDto> collect = projectSet.stream()
                .map(p -> new HomeController.ReadProjectListDto(p.getIdx(), p.getProjectName(), p.getSubject().getName(), 100))
                .collect(Collectors.toList());

        return collect;
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
