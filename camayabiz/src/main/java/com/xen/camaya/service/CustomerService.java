package com.xen.camaya.service;

import com.xen.camaya.model.CustomerModel;

public interface CustomerService {
    CustomerModel[] getAll() throws Exception;
    CustomerModel get(Integer id) throws Exception;
    CustomerModel create(CustomerModel customerModel) throws Exception;
    CustomerModel update(CustomerModel customerModel) throws Exception;
    void delete(Integer id) throws Exception;
}
