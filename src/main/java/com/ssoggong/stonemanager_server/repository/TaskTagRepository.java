package com.ssoggong.stonemanager_server.repository;

import com.ssoggong.stonemanager_server.entity.TaskTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskTagRepository extends JpaRepository<TaskTag, Long> {
}
