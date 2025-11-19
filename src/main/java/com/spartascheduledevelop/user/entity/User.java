package com.spartascheduledevelop.user.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // 고유 ID
    @Column(nullable = false, length = 50)
    private String username;  // 유저명
    @Column(nullable = false, unique = true, length = 50)
    private String email;   // 이메일
    @Column(nullable = false)
    private String password;  //비밀번호

    public User(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public void update(String email, String username, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

}
