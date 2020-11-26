package com.ssoggong.stonemanager_server.service;

import com.ssoggong.stonemanager_server.entity.Project;
import com.ssoggong.stonemanager_server.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    @Transactional
    public void saveProject(Project project) { projectRepository.save(project); }

}
