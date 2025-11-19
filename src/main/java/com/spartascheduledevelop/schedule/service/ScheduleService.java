package com.spartascheduledevelop.schedule.service;

import com.spartascheduledevelop.schedule.dto.*;
import com.spartascheduledevelop.schedule.entity.Schedule;
import com.spartascheduledevelop.schedule.repository.ScheduleRepository;
import com.spartascheduledevelop.user.entity.User;
import com.spartascheduledevelop.user.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    /**
     * 일정 생성
     */
    @Transactional
    public CreateScheduleResponse saveSchedule(CreateScheduleRequest request) {
        // 1. 유저 조회
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));

        // 2. 일정 생성
        Schedule schedule = new Schedule(user, request.getTitle(), request.getContent());

        // 3. 저장
        Schedule saved = scheduleRepository.save(schedule);

        // 4. Response 반환
        return new CreateScheduleResponse(saved);
    }

    /**
     * 일정 전체 조회
     */
    @Transactional(readOnly = true)
    public List<GetOneScheduleResponse> getAll() {
        return scheduleRepository.findAll()
                .stream()
                .map(GetOneScheduleResponse::new)
                .toList();
    }

    /**
     * 일정 단건 조회
     */
    @Transactional(readOnly = true)
    public GetOneScheduleResponse getOne(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new IllegalArgumentException("일정을 찾을 수 없습니다."));

        return new GetOneScheduleResponse(schedule);
    }

    /**
     * 일정 수정
     */
    @Transactional
    public UpdateScheduleResponse updateSchedule(Long scheduleId, UpdateScheduleRequest request) {
        // 1. 일정 조회
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new IllegalArgumentException("일정을 찾을 수 없습니다."));

        // 2. 유저 조회 (수정 시에도 유저 검증)
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));

        // 3. 수정
        schedule.update(user, request.getTitle(), request.getContent());

        // 4. Response
        return new UpdateScheduleResponse(schedule);
    }

    /**
     * 일정 삭제
     */
    @Transactional
    public void delete(Long scheduleId, DeleteScheduleRequest request) {
        // 1. 일정 존재 여부 확인
        boolean exists = scheduleRepository.existsById(scheduleId);
        if (!exists) {
            throw new IllegalStateException("존재하지 않는 일정입니다.");
        }

        // 2. 그냥 삭제 (요구사항에 비밀번호 검증 같은 조건 없음)
        scheduleRepository.deleteById(scheduleId);
    }
}
