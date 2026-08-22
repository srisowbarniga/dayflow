package com.dayflow.hrms.controller;

import com.dayflow.hrms.config.JwtUtil;
import com.dayflow.hrms.model.User;
import com.dayflow.hrms.repository.UserRepository;
import com.dayflow.hrms.service.AuthService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public AuthController(AuthService authService, UserRepository userRepository, JwtUtil jwtUtil) {
        this.authService = authService;
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/signup")
    public User signup(@RequestBody User user) {
        return authService.registerUser(user);
    }

    @PostMapping("/signin")
    public Map<String, String> signin(@RequestBody User loginRequest) {
        Optional<User> userOpt = userRepository.findByEmail(loginRequest.getEmail());

        if (userOpt.isEmpty()) {
            return Map.of("message", "User not found");
        }

        User user = userOpt.get();
        boolean matches = authService.checkPassword(loginRequest.getPassword(), user.getPassword());

        if (matches) {
            String token = jwtUtil.generateToken(user.getEmail());
            return Map.of("message", "Login successful", "token", token);
        } else {
            return Map.of("message", "Invalid password");
        }
    }
}