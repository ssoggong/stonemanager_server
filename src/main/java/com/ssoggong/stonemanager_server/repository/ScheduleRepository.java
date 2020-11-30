package com.ssoggong.stonemanager_server.repository;

import com.ssoggong.stonemanager_server.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    @Query(value = "select * from schedule where year(date) = :year and month(date) = :month and project_idx = :projectId", nativeQuery = true)
    List<Schedule> readScheduleByYearAndMonth(@Param("year") int year, @Param("month") int month, @Param("projectId") Long projectIdx);

    @Query(value = "select * from schedule where year(date) = :year and project_idx = :projectId", nativeQuery = true)
    List<Schedule> readScheduleByYear(@Param("year") int year, @Param("projectId") Long projectIdx);
}
