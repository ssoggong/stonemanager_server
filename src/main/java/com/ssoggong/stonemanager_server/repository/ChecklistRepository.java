package com.ssoggong.stonemanager_server.repository;

import com.ssoggong.stonemanager_server.entity.Checklist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChecklistRepository extends JpaRepository<Checklist, Long> {
}
