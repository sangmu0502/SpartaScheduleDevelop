package com.spartascheduledevelop.user.service;

import com.spartascheduledevelop.user.dto.UserRequest;
import com.spartascheduledevelop.user.dto.UserResponse;
import com.spartascheduledevelop.user.entity.User;
import com.spartascheduledevelop.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // Create
    public UserResponse create(UserRequest request) {

        // 이메일 중복 체크
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }

        User user = new User(
                request.getEmail(),
                request.getUsername(),
                request.getPassword()
        );

        User savedUser = userRepository.save(user);
        return new UserResponse(savedUser);
    }

    // Read All
    public List<UserResponse> getAll() {
        return userRepository.findAll().stream()
                .map(UserResponse::new)
                .toList();
    }

    // Read One
    public UserResponse getOne(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("유저가 존재하지 않습니다."));
        return new UserResponse(user);
    }

    // Update
    @Transactional
    public UserResponse update(Long userId, UserRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("유저가 존재하지 않습니다."));

        // 이메일 중복 체크 (본인 제외)
        if (!user.getEmail().equals(request.getEmail())
                && userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("이미 사용 중인 이메일입니다.");
        }

        user.update(request.getEmail(), request.getUsername(), request.getPassword());
        return new UserResponse(user);
    }

    // Delete
    public void delete(Long userId) {
        boolean exists = userRepository.existsById(userId);

        if (!exists) {
            throw new IllegalStateException("유저가 존재하지 않습니다.");
        }

        userRepository.deleteById(userId);
    }
}
