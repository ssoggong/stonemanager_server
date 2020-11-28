package com.ssoggong.stonemanager_server.repository;

import com.ssoggong.stonemanager_server.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
