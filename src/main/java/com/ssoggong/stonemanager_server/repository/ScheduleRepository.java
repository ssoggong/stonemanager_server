package com.ssoggong.stonemanager_server.repository;

import com.ssoggong.stonemanager_server.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
