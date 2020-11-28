package com.ssoggong.stonemanager_server.repository;

import com.ssoggong.stonemanager_server.entity.UserSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserScheduleRepository extends JpaRepository<UserSchedule, Long> {
}
