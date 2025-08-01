package com.procter.procter_app.controller;

import com.procter.procter_app.model.User;
import com.procter.procter_app.repo.UserRepository;
import com.procter.procter_app.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserRepository userRepo;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> req) {
        if (userRepo.findByUsername(req.get("username")).isPresent())
            return ResponseEntity.badRequest().body(Map.of("error", "User exists"));
        User user = User.builder()
                .username(req.get("username"))
                .password(encoder.encode(req.get("password")))
                .role(req.get("role").toUpperCase())
                .build();
        userRepo.save(user);
        return ResponseEntity.ok(Map.of("success", "Registered"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> req) {
        var userOpt = userRepo.findByUsername(req.get("username"));
        if (userOpt.isEmpty())
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Invalid credentials"));
        User user = userOpt.get();
        if (!encoder.matches(req.get("password"), user.getPassword()))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Invalid credentials"));
        String token = jwtService.generateToken(user.getUsername(), user.getRole());
        return ResponseEntity.ok(Map.of("token", token, "role", user.getRole()));
    }
}
