package com.spartascheduledevelop.schedule.repository;

import com.spartascheduledevelop.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

}
