package com.xen.camaya.transform;

import com.xen.camaya.entity.CustomerData;
import com.xen.camaya.model.CustomerModel;
import org.springframework.stereotype.Service;

@Service
public class TransformCustomerServImpl implements TransformCustomerServ {
    @Override
    public CustomerData toEntity(CustomerModel customerModel) {
        CustomerData customerData = new CustomerData();
        customerData.setId(customerModel.getId());
        customerData.setCustomerName(customerModel.getCustomerName());
        customerData.setCustomerEmail(customerModel.getCustomerEmail());
        customerData.setHouseName(customerModel.getHouseName());
        return customerData;
    }

    @Override
    public CustomerModel toModel(CustomerData customerData) {
        CustomerModel customerModel = new CustomerModel();
        customerModel.setId(customerData.getId());
        customerModel.setCustomerName(customerData.getCustomerName());
        customerModel.setCustomerEmail(customerData.getCustomerEmail());
        customerModel.setHouseName(customerData.getHouseName());
        return customerModel;
    }
}
