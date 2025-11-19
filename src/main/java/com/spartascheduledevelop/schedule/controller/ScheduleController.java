package com.spartascheduledevelop.schedule.controller;

import com.spartascheduledevelop.schedule.dto.*;
import com.spartascheduledevelop.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping("/schedules")
    public ResponseEntity<CreateScheduleResponse> create(
            @RequestBody CreateScheduleRequest request
            ) {
        CreateScheduleResponse result = scheduleService.saveSchedule(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/schedules")
    public ResponseEntity<List<GetOneScheduleResponse>> getAllSchedules() {
        List<GetOneScheduleResponse> result = scheduleService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/schedules/{scheduleId}")
    public ResponseEntity<GetOneScheduleResponse> getOneSchedule(@PathVariable Long scheduleId) {
        GetOneScheduleResponse result = scheduleService.getOne(scheduleId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PutMapping("/schedules/{scheduleId}")
    public ResponseEntity<UpdateScheduleResponse> updateSchedule(@RequestBody UpdateScheduleRequest request, @PathVariable Long scheduleId) {
        UpdateScheduleResponse result = scheduleService.updateSchedule(scheduleId, request);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @DeleteMapping("/schedules/{scheduleId}")
    public ResponseEntity<Void> deleteSchedule(@RequestBody DeleteScheduleRequest request, @PathVariable Long scheduleId) {
        scheduleService.delete(scheduleId, request);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }
}
