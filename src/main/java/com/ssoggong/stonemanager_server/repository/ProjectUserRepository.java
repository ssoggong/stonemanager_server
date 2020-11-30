package com.ssoggong.stonemanager_server.repository;

import com.ssoggong.stonemanager_server.entity.ProjectUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ProjectUserRepository extends JpaRepository<ProjectUser, Long> {
    @Modifying
    @Transactional
    @Query(value = "delete from project_user where project_idx = :projectId and user_idx = :userId", nativeQuery = true)
    int deleteProjectUserByUserAndProject(@Param("userId") Long userId, @Param("projectId") Long projectId);

    @Transactional
    @Query(value = "select * from project_user where project_idx = :projectId", nativeQuery = true)
    List<ProjectUser> findAllByProject(@Param("projectId") Long projectId);
}
