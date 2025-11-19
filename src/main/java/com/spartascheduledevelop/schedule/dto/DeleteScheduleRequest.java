package com.spartascheduledevelop.schedule.dto;

import lombok.Getter;

@Getter
public class DeleteScheduleRequest {
    private Long userId;
    private String title;
    private String contents;
}
