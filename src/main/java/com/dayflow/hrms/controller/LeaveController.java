package com.dayflow.hrms.controller;

import com.dayflow.hrms.model.LeaveRequest;
import com.dayflow.hrms.repository.LeaveRequestRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/leaves")
public class LeaveController {

    private final LeaveRequestRepository leaveRequestRepository;

    public LeaveController(LeaveRequestRepository leaveRequestRepository) {
        this.leaveRequestRepository = leaveRequestRepository;
    }

    // Employee applies for leave
    @PostMapping("/apply")
    public LeaveRequest applyLeave(@RequestBody LeaveRequest leaveRequest) {
        leaveRequest.setStatus("Pending");
        return leaveRequestRepository.save(leaveRequest);
    }

    // View all leaves (HR)
    @GetMapping("/all")
    public List<LeaveRequest> getAllLeaves() {
        return leaveRequestRepository.findAll();
    }

    // View pending leaves (HR)
    @GetMapping("/pending")
    public List<LeaveRequest> getPendingLeaves() {
        return leaveRequestRepository.findByStatus("Pending");
    }

    // View leaves of one employee
    @GetMapping("/employee/{employeeId}")
    public List<LeaveRequest> getEmployeeLeaves(@PathVariable Long employeeId) {
        return leaveRequestRepository.findByEmployeeId(employeeId);
    }

    // Approve leave
    @PutMapping("/{id}/approve")
    public Map<String, String> approveLeave(@PathVariable Long id, @RequestBody(required = false) Map<String, String> body) {
        Optional<LeaveRequest> opt = leaveRequestRepository.findById(id);
        if (opt.isEmpty()) return Map.of("message", "Leave request not found");

        LeaveRequest leave = opt.get();
        leave.setStatus("Approved");
        if (body != null && body.containsKey("comment")) {
            leave.setHrComment(body.get("comment"));
        }
        leaveRequestRepository.save(leave);
        return Map.of("message", "Leave approved");
    }

    // Reject leave
    @PutMapping("/{id}/reject")
    public Map<String, String> rejectLeave(@PathVariable Long id, @RequestBody(required = false) Map<String, String> body) {
        Optional<LeaveRequest> opt = leaveRequestRepository.findById(id);
        if (opt.isEmpty()) return Map.of("message", "Leave request not found");

        LeaveRequest leave = opt.get();
        leave.setStatus("Rejected");
        if (body != null && body.containsKey("comment")) {
            leave.setHrComment(body.get("comment"));
        }
        leaveRequestRepository.save(leave);
        return Map.of("message", "Leave rejected");
    }
}