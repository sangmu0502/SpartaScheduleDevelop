package com.spartascheduledevelop.user.controller;

import com.spartascheduledevelop.user.dto.UserRequest;
import com.spartascheduledevelop.user.dto.UserResponse;
import com.spartascheduledevelop.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    // Create
    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestBody UserRequest request) {
        UserResponse response = userService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Get All
    @GetMapping
    public List<UserResponse> getAll() {
        return userService.getAll();
    }

    // Get One
    @GetMapping("/{userId}")
    public UserResponse getOne(@PathVariable Long userId) {
        return userService.getOne(userId);
    }

    // Update
    @PutMapping("/{userId}")
    public ResponseEntity<UserResponse> update(
            @PathVariable Long userId,
            @RequestBody UserRequest request
    ) {
        UserResponse response = userService.update(userId, request);
        return ResponseEntity.ok(response);
    }

    // Delete
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> delete(@PathVariable Long userId) {
        userService.delete(userId);
        return ResponseEntity.ok("유저 삭제 완료");
    }
}
