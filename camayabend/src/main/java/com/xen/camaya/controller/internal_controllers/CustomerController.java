package com.xen.camaya.controller.internal_controllers;

import com.xen.camaya.model.CustomerModel;
import com.xen.camaya.service.CrudService;
import com.xen.camaya.service.CustomerService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/internal/customer")
public class CustomerController extends BaseController<CustomerModel, Integer> {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    protected String getResourceName() {
        return "customer";
    }

    @Override
    protected CrudService<CustomerModel, Integer> getService() {
        return customerService;
    }
}
