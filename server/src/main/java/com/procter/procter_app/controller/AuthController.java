package com.procter.procter_app.controller;

import com.procter.procter_app.model.User;
import com.procter.procter_app.repo.UserRepository;
import com.procter.procter_app.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthController {
    @Autowired
    UserRepository userRepo;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> req) {
        // req: { username, password, role }
        if (userRepo.findByUsername(req.get("username")).isPresent())
            return ResponseEntity.badRequest().body("User exists");
        User user = new User();
        user.setUsername(req.get("username"));
        user.setPassword(encoder.encode(req.get("password")));
        user.setRole(req.get("role").toUpperCase());
        userRepo.save(user);
        return ResponseEntity.ok("Registered");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> req) {
        Optional<User> userOpt = userRepo.findByUsername(req.get("username"));
        if (!userOpt.isPresent()) return ResponseEntity.status(401).body("Invalid credentials");
        User user = userOpt.get();
        if (!encoder.matches(req.get("password"), user.getPassword()))
            return ResponseEntity.status(401).body("Invalid credentials");
        String token = jwtService.generateToken(user.getUsername(), user.getRole());
        return ResponseEntity.ok(Map.of("token", token, "role", user.getRole()));
    }
}
