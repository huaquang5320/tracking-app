# Tracking App — Project Context

## Mục tiêu
Personal tracking app — cân nặng, bữa ăn, workout, tài chính, học tập.
Build vừa để dùng vừa để học BE Java → mục tiêu dài hạn: AI Engineer.

## Tech Stack
- **Language:** Java 17
- **Framework:** Spring Boot 3.3.0
- **Database:** PostgreSQL 18 (local), Supabase (production)
- **ORM:** Spring Data JPA + Hibernate
- **Migration:** Flyway
- **Build:** Maven
- **Utilities:** Lombok, Spring Validation
- **Deploy:** Render (app) + Supabase (DB)

## Package Structure
```
com.thomas.trackingapp/
├── TrackingAppApplication.java
├── domain/
│   ├── weight/
│   │   ├── WeightLog.java
│   │   ├── WeightLogRepository.java
│   │   ├── WeightLogService.java
│   │   ├── WeightLogController.java
│   │   └── dto/
│   │       ├── WeightLogRequest.java
│   │       └── WeightLogResponse.java
│   ├── meal/
│   │   ├── MealLog.java
│   │   ├── MealType.java (enum: BREAKFAST, LUNCH, DINNER, SNACK)
│   │   ├── MealLogRepository.java
│   │   ├── MealLogService.java
│   │   ├── MealLogController.java
│   │   └── dto/
│   │       ├── MealLogRequest.java
│   │       ├── MealLogUpdateRequest.java
│   │       ├── MealLogResponse.java
│   │       ├── MealDailySummary.java
│   │       └── MealRecentResponse.java
│   ├── workout/
│   │   ├── WorkoutSession.java      (@OneToMany → WorkoutExercise)
│   │   ├── WorkoutExercise.java     (@ManyToOne → WorkoutSession)
│   │   ├── WorkoutSessionRepository.java
│   │   ├── WorkoutExerciseRepository.java
│   │   ├── WorkoutSessionService.java
│   │   ├── WorkoutSessionController.java
│   │   └── dto/
│   │       ├── WorkoutSessionRequest.java
│   │       ├── WorkoutExerciseRequest.java
│   │       ├── WorkoutSessionResponse.java
│   │       ├── WorkoutExerciseResponse.java
│   │       ├── WorkoutExerciseHistoryResponse.java
│   │       └── WorkoutVolumeResponse.java
│   ├── finance/
│   │   ├── FinanceTransaction.java
│   │   ├── TransactionType.java (enum: INCOME, EXPENSE)
│   │   ├── FinanceTransactionRepository.java
│   │   ├── FinanceTransactionService.java
│   │   ├── FinanceTransactionController.java
│   │   └── dto/
│   │       ├── FinanceTransactionRequest.java
│   │       ├── FinanceTransactionResponse.java
│   │       └── FinanceMonthlySummary.java
│   ├── learning/
│   │   ├── LearningSession.java
│   │   ├── LearningSessionRepository.java
│   │   ├── LearningSessionService.java
│   │   ├── LearningSessionController.java
│   │   └── dto/
│   │       ├── LearningSessionRequest.java
│   │       ├── LearningSessionResponse.java
│   │       └── LearningWeeklySummary.java
│   └── goal/
│       ├── UserGoal.java
│       ├── UserGoalRepository.java
│       ├── UserGoalService.java
│       ├── UserGoalController.java
│       └── dto/
│           ├── UserGoalRequest.java
│           └── UserGoalResponse.java
└── common/
    ├── config/
    │   └── CorsConfig.java
    ├── exception/
    │   ├── GlobalExceptionHandler.java
    │   ├── DuplicateResourceException.java
    │   └── ResourceNotFoundException.java
    └── response/
        └── ErrorResponse.java
```

## Database Migrations
```
V1  — create weight_logs
V2  — create meal_logs
V3  — create workout_sessions
V4  — create workout_exercises (FK → workout_sessions)
V5  — create finance_transactions
V6  — create learning_sessions
V7  — alter meal_logs: food_name nullable, calories NOT NULL
V8  — alter workout_sessions: add started_at, ended_at; drop duration_min
V9  — alter workout_exercises: add rpe, rest_seconds, note
V10 — drop workout_sessions.logged_date
V11 — add index idx_workout_sessions_user_started
V12 — create user_goals (UNIQUE user_id)
```

## API Endpoints

### Weight
```
POST   /api/v1/weight-logs              → tạo log
GET    /api/v1/weight-logs/today        → log hôm nay
GET    /api/v1/weight-logs/trend?from=&to= → trend theo khoảng
```

### Meal
```
POST   /api/v1/meal-logs                → tạo meal
GET    /api/v1/meal-logs?date=          → list theo ngày
GET    /api/v1/meal-logs/summary?date=  → tổng calories/macro ngày
GET    /api/v1/meal-logs/recent?limit=  → top N unique meals theo food_name (default 10, max 30)
PUT    /api/v1/meal-logs/{id}           → update (trừ logged_date)
DELETE /api/v1/meal-logs/{id}           → xóa
```

### Workout
```
POST   /api/v1/workout-sessions                     → tạo session + exercises
GET    /api/v1/workout-sessions?from=&to=           → list theo khoảng
GET    /api/v1/workout-sessions/{id}                → chi tiết 1 session
DELETE /api/v1/workout-sessions/{id}                → xóa (cascade exercises)
GET    /api/v1/workout-sessions/{id}/volume         → tổng volume buổi tập
GET    /api/v1/workout-sessions/exercises/history?name=&from=&to= → lịch sử bài tập
```

### Finance
```
POST   /api/v1/finance-transactions             → tạo giao dịch
GET    /api/v1/finance-transactions?from=&to=   → list theo khoảng
PUT    /api/v1/finance-transactions/{id}        → update
DELETE /api/v1/finance-transactions/{id}        → xóa
GET    /api/v1/finance-transactions/summary?from=&to= → tổng thu/chi/số dư
```

### Learning
```
POST   /api/v1/learning-sessions             → tạo session
GET    /api/v1/learning-sessions?from=&to=   → list theo khoảng
PUT    /api/v1/learning-sessions/{id}        → update
DELETE /api/v1/learning-sessions/{id}        → xóa
GET    /api/v1/learning-sessions/summary?from=&to= → tổng giờ theo topic
```

### User Goal (nutrition target)
```
GET    /api/v1/user-goals                   → goal hiện tại (404 nếu chưa set)
PUT    /api/v1/user-goals                   → upsert goal (kcal + macro/ngày)
```

## Conventions
- **Git:** GitHub Flow (main + feature branches)
- **Commits:** Conventional Commits (feat:, fix:, chore:, docs:, refactor:)
- **Auth:** TEMP_USER_ID = 1L hardcoded — chưa implement
- **Error handling:** GlobalExceptionHandler → ErrorResponse {code, message, timestamp}
- **CORS:** allowedOrigins localhost:3000, localhost:5173

## Key Design Decisions
- Package theo domain (không theo layer)
- BIGSERIAL PK, DATE cho logged_date, TIMESTAMP cho created_at
- UNIQUE (user_id, logged_date) chỉ trên weight_logs
- Composite index (user_id, logged_date DESC) trên tất cả domain
- Workout dùng 2 table: workout_sessions + workout_exercises
- DTO tách Request/UpdateRequest/Response

## Roadmap
- [x] Phase 1a: DB design + Flyway migrations
- [x] Phase 1b: Weight domain CRUD
- [x] Phase 1c: Meal, Workout, Finance, Learning domains
- [ ] Phase 1d: Auth (Spring Security + JWT)
- [ ] Phase 2: Docker, system design
- [ ] Phase 3: AI layer (LangChain4j, RAG, LLM meal parsing)

## Local Setup
```bash
# Prerequisites: Java 17, Maven, PostgreSQL
# 1. Tạo DB
psql -U postgres -c "CREATE DATABASE tracking_db;"

# 2. Tạo .env
DB_URL=jdbc:postgresql://localhost:5432/tracking_db
DB_USERNAME=postgres
DB_PASSWORD=yourpassword

# 3. Run
mvn spring-boot:run
```

## Environment Variables
```
DB_URL      → JDBC connection string
DB_USERNAME → DB username
DB_PASSWORD → DB password
PORT        → Server port (Render inject tự động, default 8080)
```
