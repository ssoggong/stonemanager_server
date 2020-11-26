package com.ssoggong.stonemanager_server.repository;

import com.ssoggong.stonemanager_server.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
