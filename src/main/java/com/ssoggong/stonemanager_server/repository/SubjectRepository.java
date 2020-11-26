package com.ssoggong.stonemanager_server.repository;

import com.ssoggong.stonemanager_server.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
