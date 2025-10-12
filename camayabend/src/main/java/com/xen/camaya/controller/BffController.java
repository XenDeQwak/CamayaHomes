package com.xen.camaya.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xen.camaya.model.UserModel;
import com.xen.camaya.service.UserService;

@RestController
@RequestMapping("/api/user")
public class BffController {
    
    private final UserService userService;

    public BffController(UserService userService) {
        this.userService = userService;
    }
    
    @GetMapping
    public List<UserModel> getAllUsers() {
        return userService.getAll();
    }

    @PostMapping
    public UserModel createUser(@RequestBody UserModel user) {
        return userService.create(user);
    }
    
}
