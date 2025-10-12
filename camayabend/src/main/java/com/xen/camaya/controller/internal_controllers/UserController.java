package com.xen.camaya.controller.internal_controllers;

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

}
