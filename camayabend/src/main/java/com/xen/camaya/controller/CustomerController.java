package com.xen.camaya.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {
    Logger logger = LoggerFactory.getLogger(CustomerController.class);
    @Autowired
    private CustomerService customerService;

}
