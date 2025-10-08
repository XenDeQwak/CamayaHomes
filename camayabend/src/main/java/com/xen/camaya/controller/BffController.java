package com.xen.camaya.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xen.camaya.model.CustomerModel;
import com.xen.camaya.service.CustomerService;

@RestController
@RequestMapping("/api/customer")
public class BffController {
    
    private final CustomerService customerService;

    public BffController(CustomerService customerService) {
        this.customerService = customerService;
    }
    
    @GetMapping
    public List<CustomerModel> getAllCustomers() {
        return customerService.getAll();
    }

    @PostMapping
    public CustomerModel createCustomer(@RequestBody CustomerModel customer) {
        return customerService.create(customer);
    }
    
}
