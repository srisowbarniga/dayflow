# Dayflow — HR Management System

**Every workday, perfectly aligned.**

Dayflow is a Human Resource Management System (HRMS) built to digitize core HR operations — authentication, attendance, leave management, and approval workflows — with a live analytics dashboard for HR/Admins.

## 🚀 Features

### 🔐 Authentication
- User signup and signin
- Role-based access (Employee / HR)
- Secure password storage (BCrypt encryption)
- JWT token-based session authentication

### 🕒 Attendance Management
- Employee check-in / check-out
- Attendance status tracking (Present, Absent, Half-day, Leave)
- View attendance records (individual and company-wide)

### 📝 Leave Management
- Apply for leave (Paid / Sick / Unpaid)
- Track leave request status (Pending / Approved / Rejected)
- HR approval workflow with comments
- Live status sync back to employee records

### 📊 Analytics Dashboard
- Total employees, total leaves at a glance
- Pending / Approved / Rejected leave breakdown
- Live HR console with real-time data refresh

## 🛠️ Technologies Used

**Backend:** Java 17, Spring Boot 4.1.1, Spring Data JPA (Hibernate), Spring Security, JWT (jjwt), Maven

**Database:** MySQL

**Frontend:** HTML5, CSS3, JavaScript (vanilla), Fetch API

**Development Tools:** Visual Studio Code, Git & GitHub, Postman / PowerShell

## 📡 API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | /api/auth/signup | Register a new user |
| POST | /api/auth/signin | Login (returns JWT token) |
| POST | /api/leaves/apply | Apply for leave |
| GET | /api/leaves/all | View all leave requests |
| GET | /api/leaves/pending | View pending leave requests |
| PUT | /api/leaves/{id}/approve | Approve a leave request |
| PUT | /api/leaves/{id}/reject | Reject a leave request |
| POST | /api/attendance/checkin | Check in |
| PUT | /api/attendance/{id}/checkout | Check out |
| GET | /api/attendance/all | View all attendance records |
| GET | /api/analytics/summary | HR analytics summary |

## ▶️ Running the Project

```bash
./mvnw spring-boot:run
```

Then open `http://localhost:8080` to view the live HR dashboard.
