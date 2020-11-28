package com.ssoggong.stonemanager_server.repository;

import com.ssoggong.stonemanager_server.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
