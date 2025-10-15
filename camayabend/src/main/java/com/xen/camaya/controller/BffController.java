package com.xen.camaya.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.xen.camaya.model.UserModel;
import com.xen.camaya.service.UserService;

@RestController
@RequestMapping("/api/users")
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

    @PostMapping("/{userId}/assign/{adminId}")
    public ResponseEntity<?> assignAdmin(@PathVariable Integer userId, @PathVariable Integer adminId) {
        String internalUrl = "http://localhost:8080/internal/users/" + userId + "/assign/" + adminId;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(internalUrl, null, String.class);
    }

    
    
}
