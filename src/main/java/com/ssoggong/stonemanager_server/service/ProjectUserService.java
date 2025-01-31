package com.ssoggong.stonemanager_server.service;

import com.ssoggong.stonemanager_server.entity.Project;
import com.ssoggong.stonemanager_server.entity.ProjectUser;
import com.ssoggong.stonemanager_server.entity.User;
import com.ssoggong.stonemanager_server.exception.MultipleNotFoundException;
import com.ssoggong.stonemanager_server.exception.NotFoundException;
import com.ssoggong.stonemanager_server.repository.ProjectUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProjectUserService {

    private final ProjectUserRepository projectUserRepository;

    @Transactional
    public void saveProjectUser(ProjectUser projectUser) { projectUserRepository.save(projectUser); }

    @Transactional
    public void deleteProjectUser(Long userId, Long projectId){
        if(projectUserRepository.deleteProjectUserByUserAndProject(userId, projectId) == 0) throw new MultipleNotFoundException();
    }

    @Transactional
    public void createProjectUser(User user, Project project){
        ProjectUser projectUser = ProjectUser.builder()
                .user(user)
                .project(project)
                .build();
        saveProjectUser(projectUser);
    }
}
