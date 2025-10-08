package com.xen.camaya.controller.internal_controllers;

import com.xen.camaya.model.CustomerModel;
import com.xen.camaya.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    private final CustomerService customerService;

    public HomeController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public String home(Model model) {
        List<CustomerModel> customers = customerService.getAll();
        model.addAttribute("customers", customers);
        model.addAttribute("newCustomer", new CustomerModel());
        return "index";
    }

    @PostMapping("/add-customer")
    public String addCustomer(@ModelAttribute CustomerModel newCustomer) {
        customerService.create(newCustomer);
        return "redirect:/";
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }
}
