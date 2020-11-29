package com.ssoggong.stonemanager_server.repository;

import com.ssoggong.stonemanager_server.entity.Task;
import com.ssoggong.stonemanager_server.entity.TaskTag;
import com.ssoggong.stonemanager_server.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {


}
