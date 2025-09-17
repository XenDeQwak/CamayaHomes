package com.xen.camaya.serviceimpl;

import com.xen.camaya.entity.CustomerData;
import com.xen.camaya.model.CustomerModel;
import com.xen.camaya.repository.CustomerRepository;
import com.xen.camaya.service.CustomerService;
import com.xen.camaya.transform.TransformCustomerServ;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final List<CustomerModel> customers = new ArrayList<>();
    private final TransformCustomerServ transformCustomerServ;
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository,
                               TransformCustomerServ transformCustomerServ) {
        this.customerRepository = customerRepository;
        this.transformCustomerServ = transformCustomerServ;
    }

    @Override
    public CustomerModel create(CustomerModel customerModel) {
        CustomerData entity = transformCustomerServ.toEntity(customerModel);
        CustomerData saved = customerRepository.save(entity);
        return transformCustomerServ.toModel(saved);
    }

    @Override
    public List<CustomerModel> getAll() {
        List<CustomerData> entities = (List<CustomerData>) customerRepository.findAll();
        List<CustomerModel> models = new ArrayList<>();
        for (CustomerData entity : entities) {
            models.add(transformCustomerServ.toModel(entity));
        }
        return models;
    }

    @Override
    public CustomerModel get(Integer id) {
        return customerRepository.findById(id)
                .map(transformCustomerServ::toModel)
                .orElse(null);
    }


    @Override
    public CustomerModel update(CustomerModel entity) {
        return entity;
    }

    @Override
    public void delete(Integer id) {
        customers.removeIf(c -> c.getId() == id);
    }
}
