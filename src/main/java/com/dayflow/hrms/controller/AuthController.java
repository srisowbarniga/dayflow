package com.dayflow.hrms.controller;

import com.dayflow.hrms.model.User;
import com.dayflow.hrms.repository.UserRepository;
import com.dayflow.hrms.service.AuthService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final UserRepository userRepository;

    public AuthController(AuthService authService, UserRepository userRepository) {
        this.authService = authService;
        this.userRepository = userRepository;
    }

    @PostMapping("/signup")
    public User signup(@RequestBody User user) {
        return authService.registerUser(user);
    }

    @PostMapping("/signin")
    public String signin(@RequestBody User loginRequest) {
        Optional<User> userOpt = userRepository.findByEmail(loginRequest.getEmail());

        if (userOpt.isEmpty()) {
            return "User not found";
        }

        User user = userOpt.get();
        boolean matches = authService.checkPassword(loginRequest.getPassword(), user.getPassword());

        if (matches) {
            return "Login successful";
        } else {
            return "Invalid password";
        }
    }
}