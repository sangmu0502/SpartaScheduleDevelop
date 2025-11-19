package com.spartascheduledevelop.user.service;

import com.spartascheduledevelop.user.dto.LoginRequest;
import com.spartascheduledevelop.user.dto.LoginResponse;
import com.spartascheduledevelop.user.entity.User;
import com.spartascheduledevelop.user.repository.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    public LoginResponse login(LoginRequest request, HttpServletRequest httpRequest, HttpServletResponse httpResponse) {

        // 1. 이메일 사용자 조회
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "이메일 또는 비밀번호가 일치하지 않습니다."));

        // 2. 비밀번호 검증
        if (!user.getPassword().equals(request.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "이메일 또는 비밀번호가 일치하지 않습니다.");
        }

        // 3. 세션 생성
        HttpSession session = httpRequest.getSession(true);
        session.setAttribute("userId", user.getId());

        // 4. 세션 ID 쿠키로 추가
        Cookie cookie = new Cookie("JSESSIONID", session.getId());
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        httpResponse.addCookie(cookie);

        // 5. 로그인 응답 반환
        return new LoginResponse(user.getId(), user.getUsername());
    }


    public void logout(HttpServletRequest httpRequest) {
        HttpSession session = httpRequest.getSession(false);

        if (session != null) {
            session.invalidate();
        }
    }
}
