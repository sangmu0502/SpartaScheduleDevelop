# 🗓️ Spring Boot 일정 관리 프로젝트

Spring Boot + JPA + H2 + Cookie/Session 인증 기반의 일정 관리 프로젝트입니다.  
사용자(User)와 일정(Schedule)을 CRUD로 관리하고,  
이메일/비밀번호 기반 로그인 기능을 제공합니다.

---

## 📁 프로젝트 주요 기능

- 일정 CRUD (생성/조회/수정/삭제)
- 유저 CRUD
- JPA Auditing 기반 createdAt / modifiedAt 자동 관리
- 회원가입
- 로그인/로그아웃 (Cookie + Session)
- 사용자와 일정 간 N:1 연관관계

---

## 🧱 ERD 구조

User (1) ──── (N) Schedule

### 📌 User 테이블
| 필드 | 타입 | 설명 |
|------|-------|--------|
| id | BIGINT (PK) | 사용자 고유 ID |
| username | VARCHAR | 사용자명 |
| email | VARCHAR | 이메일 (Unique) |
| password | VARCHAR | 비밀번호 |
| createdAt | DATETIME | 생성일 |
| modifiedAt | DATETIME | 수정일 |

### 📌 Schedule 테이블
| 필드 | 타입 | 설명 |
|------|-------|--------|
| id | BIGINT (PK) | 일정 고유 ID |
| user_id | BIGINT (FK) | 작성자 ID |
| title | VARCHAR | 일정 제목 |
| content | TEXT | 일정 내용 |
| createdAt | DATETIME | 생성일 |
| modifiedAt | DATETIME | 수정일 |

---

## 📌 API 명세서

### 🧑‍🤝‍🧑 User API

| 기능 | Method | URL | 요청 Body | 응답 |
|------|--------|------|------------|--------|
| 회원가입 | POST | `/users` | email, username, password | 201 Created |
| 전체 유저 조회 | GET | `/users` | - | 200 OK |
| 단일 유저 조회 | GET | `/users/{userId}` | - | 200 OK |
| 유저 수정 | PUT | `/users/{userId}` | email, username, password | 200 OK |
| 유저 삭제 | DELETE | `/users/{userId}` | - | 204 No Content |

---

### 🔐 Auth API

| 기능 | Method | URL | 요청 Body | 응답 |
|------|--------|-------|-------------|-----------|
| 로그인 | POST | `/login` | email, password | 200 OK (세션 생성) |
| 로그아웃 | POST | `/logout` | - | 200 OK (세션 삭제) |

로그인 실패 시 → **401 Unauthorized**

---

### 📅 Schedule API

| 기능 | Method | URL | 요청 Body | 응답 |
|------|--------|-------|--------------|-----------|
| 일정 생성 | POST | `/schedules` | userId, title, content | 201 Created |
| 일정 전체 조회 | GET | `/schedules` | - | 200 OK |
| 일정 상세 조회 | GET | `/schedules/{scheduleId}` | - | 200 OK |
| 일정 수정 | PUT | `/schedules/{scheduleId}` | userId, title, content | 200 OK |
| 일정 삭제 | DELETE | `/schedules/{scheduleId}` | userId | 204 No Content |

---

