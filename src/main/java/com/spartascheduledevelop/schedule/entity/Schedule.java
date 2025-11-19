package com.spartascheduledevelop.schedule.entity;

import com.spartascheduledevelop.common.entity.BaseEntity;
import com.spartascheduledevelop.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "schedules")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // 고유 Id

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;          //유저

    private String title;  // 일정 제목
    private String content;  // 일정 내용

    public Schedule(User user, String title, String content) {
        this.user = user;
        this.title = title;
        this.content = content;
    }

    public void update(User user, String title, String content) {
        this.user = user;
        this.title = title;
        this.content = content;
    }
}
