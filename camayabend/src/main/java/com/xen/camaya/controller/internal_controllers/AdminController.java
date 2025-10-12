package com.xen.camaya.controller.internal_controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xen.camaya.model.AdminModel;
import com.xen.camaya.service.AdminService;
import com.xen.camaya.service.CrudService;

@RestController
@RequestMapping("/internal/admin")
public class AdminController extends BaseController<AdminModel, Integer>{

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    protected String getResourceName() {
        return "admin";
    }

    @Override
    protected CrudService<AdminModel, Integer> getService() {
        return adminService;
    }

}
