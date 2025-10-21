package com.xen.camaya.controller.internal_controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xen.camaya.model.UserModel;
import com.xen.camaya.service.CrudService;
import com.xen.camaya.service.PropertyService;
import com.xen.camaya.service.UserService;

@RestController
@RequestMapping("/internal/users")
public class UserController extends BaseController<UserModel, Integer> {

    private final UserService userService;

    
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected String getResourceName() {
        return "user";
    }

    @Override
    protected CrudService<UserModel, Integer> getService() {
        return userService;
    }

    @PostMapping("/{userId}/assign/{adminId}")
    public ResponseEntity<?> assignAdmin(@PathVariable Integer userId, @PathVariable Integer adminId) {
        try {
            boolean success = userService.assign(userId, adminId);
            if (!success) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Assignment failed");
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/link/{adminId}")
    public ResponseEntity<?> getLinkedCustomers(@PathVariable Integer adminId) {
        try {
            List<UserModel> linked = userService.getLinkedCustomers(adminId);
            return ResponseEntity.ok(linked);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/{propertyId}/linked/{userId}")
    public ResponseEntity<?> assignProperty(@PathVariable Integer propertyId, @PathVariable Integer userId) {
        try {
            boolean success = userService.assignPropertyToCustomer(propertyId, userId);
            if(success) return ResponseEntity.ok().build();
            else return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


}
