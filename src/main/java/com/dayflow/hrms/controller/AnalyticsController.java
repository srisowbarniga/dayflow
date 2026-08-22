package com.dayflow.hrms.controller;

import com.dayflow.hrms.repository.LeaveRequestRepository;
import com.dayflow.hrms.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/analytics")
public class AnalyticsController {

    private final UserRepository userRepository;
    private final LeaveRequestRepository leaveRequestRepository;

    public AnalyticsController(UserRepository userRepository, LeaveRequestRepository leaveRequestRepository) {
        this.userRepository = userRepository;
        this.leaveRequestRepository = leaveRequestRepository;
    }

    @GetMapping("/summary")
    public Map<String, Object> getSummary() {
        Map<String, Object> summary = new HashMap<>();

        long totalEmployees = userRepository.count();
        long totalLeaves = leaveRequestRepository.count();
        long pendingLeaves = leaveRequestRepository.findByStatus("Pending").size();
        long approvedLeaves = leaveRequestRepository.findByStatus("Approved").size();
        long rejectedLeaves = leaveRequestRepository.findByStatus("Rejected").size();

        summary.put("totalEmployees", totalEmployees);
        summary.put("totalLeaves", totalLeaves);
        summary.put("pendingLeaves", pendingLeaves);
        summary.put("approvedLeaves", approvedLeaves);
        summary.put("rejectedLeaves", rejectedLeaves);

        return summary;
    }
}