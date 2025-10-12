package com.xen.camaya.serviceimpl;

import com.xen.camaya.entity.CustomerData;
import com.xen.camaya.model.CustomerModel;
import com.xen.camaya.repository.CustomerRepository;
import com.xen.camaya.service.CustomerService;
import com.xen.camaya.transform.TransformCustomerServ;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl extends BaseServiceImpl<CustomerModel, CustomerData, Integer> implements CustomerService {

    private final TransformCustomerServ transformCustomerServ;

    public CustomerServiceImpl(CustomerRepository customerRepository, TransformCustomerServ transformCustomerServ) {
        super(customerRepository);
        this.transformCustomerServ = transformCustomerServ;
    }

    @Override
    protected CustomerData toEntity(CustomerModel model) {
        return transformCustomerServ.toEntity(model);
    }

    @Override
    protected CustomerModel toModel(CustomerData entity) {
        return transformCustomerServ.toModel(entity);
    }
}
