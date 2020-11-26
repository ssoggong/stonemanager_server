package com.ssoggong.stonemanager_server.repository;

import com.ssoggong.stonemanager_server.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
