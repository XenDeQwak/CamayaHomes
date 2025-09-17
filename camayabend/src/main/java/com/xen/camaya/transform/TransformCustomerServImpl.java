package com.xen.camaya.transform;

import com.xen.camaya.entity.CustomerData;
import com.xen.camaya.model.CustomerModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class TransformCustomerServImpl implements TransformCustomerServ {
    private final ModelMapper mapper = new ModelMapper();
    @Override
    public CustomerData toEntity(CustomerModel customerModel) {
        return mapper.map(customerModel, CustomerData.class);
    }

    @Override
    public CustomerModel toModel(CustomerData customerData) {
        return mapper.map(customerData, CustomerModel.class);
    }
}
