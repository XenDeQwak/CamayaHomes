package com.xen.camaya.controller;

import com.xen.camaya.entity.CustomerData;
import com.xen.camaya.repository.CustomerRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    private final CustomerRepository customerRepository;

    public LoginController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String doLogin(@RequestParam String customerEmail,
                          @RequestParam String customerPassword,
                          Model model) {
        CustomerData user = customerRepository.findByCustomerEmailAndCustomerPassword(customerEmail, customerPassword);
        if (user != null) {
            return "redirect:/test";
        } else {
            model.addAttribute("error", "Invalid email or password");
            return "login";
        }
    }
}
