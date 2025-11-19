package com.spartascheduledevelop.schedule.dto;

import com.spartascheduledevelop.schedule.entity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GetOneScheduleResponse {
    private final Long id;
    private final Long userId;
    private final String title;
    private final String content;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public GetOneScheduleResponse(Schedule schedule) {
        this.id = schedule.getId();
        this.userId = schedule.getUser().getId();
        this.title = schedule.getTitle();
        this.content = schedule.getContent();
        this.createdAt = schedule.getCreatedAt();
        this.modifiedAt = schedule.getModifiedAt();
    }
}
