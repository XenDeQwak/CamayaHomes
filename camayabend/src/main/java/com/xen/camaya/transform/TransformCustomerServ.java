package com.xen.camaya.transform;

import com.xen.camaya.entity.CustomerData;
import com.xen.camaya.model.CustomerModel;

public interface TransformCustomerServ {
    CustomerData toEntity(CustomerModel customerModel);
    CustomerModel toModel(CustomerData customerData);
}
