package com.xen.camaya.controller.internal_controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xen.camaya.model.UserModel;
import com.xen.camaya.service.CrudService;
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

}
