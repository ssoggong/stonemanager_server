package com.ssoggong.stonemanager_server.repository;

import com.ssoggong.stonemanager_server.entity.ProjectUser;
import com.ssoggong.stonemanager_server.entity.UserSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface UserSubjectRepository extends JpaRepository<UserSubject, Long> {
    @Transactional
    @Query(value = "select * from user_subject where subject_idx = :subjectId and user_idx = :userId", nativeQuery = true)
    List<UserSubject> findByUserAndSubject(@Param("subjectId") Long subjectIdx,
                                               @Param("userId") Long userIdx);
}
