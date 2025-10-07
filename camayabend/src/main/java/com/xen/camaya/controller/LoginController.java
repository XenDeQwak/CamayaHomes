package com.xen.camaya.controller;

import com.xen.camaya.entity.CustomerData;
import com.xen.camaya.repository.CustomerRepository;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class LoginController {
    private final CustomerRepository customerRepository;

    public LoginController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> payload) {
        String email = payload.get("customerEmail");
        String password = payload.get("customerPassword");
        
        CustomerData user = customerRepository.findByCustomerEmailAndCustomerPassword(email, password);

        if (user != null) {
            return Map.of(
                "success", true,
                "customerName", user.getCustomerName()
            );
        } else {
            return Map.of("success", false, 
            "message", "Invalid email or password");
        }
    }
}
