package com.xen.camaya.controller.internal_controllers;

import com.xen.camaya.entity.UserData;
import com.xen.camaya.repository.UserRepository;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class LoginController {
    private final UserRepository userRepository;

    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> payload) {
        String email = payload.get("email");
        String password = payload.get("password");
        
        UserData user = userRepository.findByEmailAndPassword(email, password);

        if (user.getRole().equals("admin")) {
            return Map.of(
                "success", true,
                "userRole", user.getRole()
            );
        } else {
            return Map.of("success", false, 
            "message", "user is customer");
        }
    }
}
