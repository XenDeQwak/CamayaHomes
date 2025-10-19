package com.xen.camaya.controller.internal_controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xen.camaya.model.PropertyModel;
import com.xen.camaya.service.CrudService;
import com.xen.camaya.service.PropertyService;

@RestController
@RequestMapping("/internal/property")
public class PropertyController extends BaseController<PropertyModel, Integer> {

    private final PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @Override
    protected String getResourceName() {
        return "property";
    }

    @Override
    protected CrudService<PropertyModel, Integer> getService() {
        return propertyService;
    }
}
