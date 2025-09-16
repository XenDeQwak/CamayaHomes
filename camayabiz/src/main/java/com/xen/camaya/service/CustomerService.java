package com.xen.camaya.service;

import com.xen.camaya.model.CustomerModel;

import java.util.List;

public interface CustomerService extends CrudService<CustomerModel, Integer> {
    List<CustomerModel> getAll();
    CustomerModel get(Integer id);
    CustomerModel create(CustomerModel customerModel);
    CustomerModel update(CustomerModel customerModel);
    void delete(Integer id);
}
