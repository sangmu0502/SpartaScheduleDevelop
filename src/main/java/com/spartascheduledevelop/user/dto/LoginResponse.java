package com.spartascheduledevelop.user.dto;

import lombok.Getter;

@Getter
public class LoginResponse {
    private final Long userId;
    private final String username;

    public LoginResponse(Long userId, String username) {
        this.userId = userId;
        this.username = username;
    }
}
