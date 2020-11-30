package com.ssoggong.stonemanager_server.repository;

import com.ssoggong.stonemanager_server.entity.Student;
import com.ssoggong.stonemanager_server.entity.SubjectStudent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByStudentId(String studentId);
}
