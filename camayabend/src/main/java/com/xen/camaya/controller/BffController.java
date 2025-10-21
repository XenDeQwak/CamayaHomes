package com.xen.camaya.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import com.xen.camaya.model.UserModel;
import com.xen.camaya.model.PropertyModel;
import com.xen.camaya.service.UserService;
import com.xen.camaya.service.PropertyService;

@RestController
@RequestMapping("/api/proxy")
public class BffController {

    private final UserService userService;
    private final PropertyService propertyService;

    public BffController(UserService userService, PropertyService propertyService) {
        this.userService = userService;
        this.propertyService = propertyService;
    }

    @GetMapping("/users")
    public List<UserModel> getAllUsers() {
        return userService.getAll();
    }

    @PostMapping("/users")
    public UserModel createUser(@RequestBody UserModel user) {
        return userService.create(user);
    }

    @PostMapping("/users/{userId}/assign/{adminId}")
    public ResponseEntity<?> assignAdmin(@PathVariable Integer userId, @PathVariable Integer adminId) {
        String internalUrl = "http://localhost:8080/internal/users/" + userId + "/assign/" + adminId;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(internalUrl, null, String.class);
    }

    @PostMapping("/users/{propertyId}/linked/{userId}")
    public ResponseEntity<?> assignProperty(@PathVariable Integer propertyId, @PathVariable Integer userId) {
        String internalUrl = "http://localhost:8080/internal/users/" + propertyId + "/linked/" + userId;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(internalUrl, null, String.class);
    }

    @GetMapping("/users/link/{adminId}")
    public ResponseEntity<?> getLinkedCustomers(@PathVariable Integer adminId) {
        String internalUrl = "http://localhost:8080/internal/users/link/" + adminId;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForEntity(internalUrl, Object.class);
    }

    @GetMapping("/properties")
    public List<PropertyModel> getAllProperties() {
        return propertyService.getAll();
    }

    @PostMapping("/properties")
    public PropertyModel createProperty(@RequestBody PropertyModel property) {
        return propertyService.create(property);
    }
}
