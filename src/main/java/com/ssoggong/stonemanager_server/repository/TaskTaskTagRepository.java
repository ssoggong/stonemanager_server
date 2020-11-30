package com.ssoggong.stonemanager_server.repository;

import com.ssoggong.stonemanager_server.entity.Task;
import com.ssoggong.stonemanager_server.entity.TaskTag;
import com.ssoggong.stonemanager_server.entity.TaskTaskTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskTaskTagRepository extends JpaRepository<TaskTaskTag, Long> {

    List<TaskTaskTag> findAllByTaskTag(TaskTag taskTag);
}
