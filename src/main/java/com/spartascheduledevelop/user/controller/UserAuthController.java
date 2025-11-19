package com.spartascheduledevelop.user.controller;

import com.spartascheduledevelop.user.dto.LoginRequest;
import com.spartascheduledevelop.user.dto.LoginResponse;
import com.spartascheduledevelop.user.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserAuthController {

    private final AuthService authService;

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @RequestBody LoginRequest request,
            HttpServletRequest httpRequest,
            HttpServletResponse httpResponse
    ) {
        LoginResponse result = authService.login(request, httpRequest, httpResponse);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 로그아웃
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest httpRequest) {
        authService.logout(httpRequest);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
