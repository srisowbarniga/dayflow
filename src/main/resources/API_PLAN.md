# API Endpoints — Approval & Analytics Module

## Approval Workflow
| Method | Endpoint                     | Description                        |
|--------|-------------------------------|-------------------------------------|
| GET    | /api/leaves/pending            | List all pending leave requests    |
| GET    | /api/leaves/{id}               | Get single leave request details   |
| PUT    | /api/leaves/{id}/approve       | Approve a leave request            |
| PUT    | /api/leaves/{id}/reject        | Reject a leave request (with comment) |

## Analytics & Reports
| Method | Endpoint                     | Description                        |
|--------|-------------------------------|-------------------------------------|
| GET    | /api/analytics/summary        | Total employees, present today, absent, on leave |
| GET    | /api/analytics/leave-trend     | Monthly/weekly leave count          |
| GET    | /api/analytics/leave-type      | Breakdown by Paid/Sick/Unpaid       |
| GET    | /api/analytics/attendance-rate | % attendance overall                |