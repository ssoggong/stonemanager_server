package com.ssoggong.stonemanager_server.repository;

import com.ssoggong.stonemanager_server.entity.UserTask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTaskRepository extends JpaRepository<UserTask, Long> {
}
