package com.xen.camaya.service;
import com.xen.camaya.model.CustomerModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CustomerService extends BaseService<CustomerModel> {
    public CustomerService (
            @Value("${service.api.customer.endpoint:http://localhost:8080/api/customer}") String endpointUrl
    ) {
        super(endpointUrl, CustomerModel.class, CustomerModel[].class);
    }
}

