package com.ssoggong.stonemanager_server.repository;

import com.ssoggong.stonemanager_server.entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
}
