package com.dayflow.hrms.controller;

import com.dayflow.hrms.model.Attendance;
import com.dayflow.hrms.repository.AttendanceRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    private final AttendanceRepository attendanceRepository;

    public AttendanceController(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    // Check in
    @PostMapping("/checkin")
    public Attendance checkIn(@RequestBody Attendance attendance) {
        attendance.setDate(LocalDate.now());
        attendance.setCheckIn(LocalTime.now());
        attendance.setStatus("Present");
        return attendanceRepository.save(attendance);
    }

    // Check out
    @PutMapping("/{id}/checkout")
    public Map<String, String> checkOut(@PathVariable Long id) {
        Optional<Attendance> opt = attendanceRepository.findById(id);
        if (opt.isEmpty()) return Map.of("message", "Attendance record not found");

        Attendance attendance = opt.get();
        attendance.setCheckOut(LocalTime.now());
        attendanceRepository.save(attendance);
        return Map.of("message", "Checked out successfully");
    }

    // View all attendance (HR)
    @GetMapping("/all")
    public List<Attendance> getAllAttendance() {
        return attendanceRepository.findAll();
    }

    // View one employee's attendance
    @GetMapping("/employee/{employeeId}")
    public List<Attendance> getEmployeeAttendance(@PathVariable Long employeeId) {
        return attendanceRepository.findByEmployeeId(employeeId);
    }
}