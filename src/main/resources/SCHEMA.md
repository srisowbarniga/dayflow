# Leave Requests Table

| Field        | Type      | Description                          |
|--------------|-----------|---------------------------------------|
| id           | Long      | Primary key                          |
| employee_id  | Long      | Foreign key → users table             |
| leave_type   | String    | Paid / Sick / Unpaid                  |
| start_date   | Date      | Leave start date                      |
| end_date     | Date      | Leave end date                        |
| remarks      | String    | Employee's reason for leave           |
| status       | String    | Pending / Approved / Rejected         |
| hr_comment   | String    | HR's comment while approving/rejecting|
| approved_by  | Long      | HR/Admin user id who approved         |