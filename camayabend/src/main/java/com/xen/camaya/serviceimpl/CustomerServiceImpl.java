package com.xen.camaya.serviceimpl;

import com.xen.camaya.entity.CustomerData;
import com.xen.camaya.model.CustomerModel;
import com.xen.camaya.repository.CustomerRepository;
import com.xen.camaya.service.CustomerService;
import com.xen.camaya.transform.TransformCustomerServ;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final TransformCustomerServ transform;

    public CustomerServiceImpl(CustomerRepository customerRepository, TransformCustomerServ transform) {
        this.customerRepository = customerRepository;
        this.transform = transform;
    }

    @Override
    public CustomerModel create(CustomerModel entity) {
        CustomerData saved = customerRepository.save(transform.toEntity(entity));
        return transform.toModel(saved);
    }

    @Override
    public CustomerModel update(CustomerModel entity) {
        CustomerData saved = customerRepository.save(transform.toEntity(entity));
        return transform.toModel(saved);
    }

    @Override
    public CustomerModel get(Integer id) {
        Optional<CustomerData> data = customerRepository.findById(id);
        return data.map(transform::toModel).orElse(null);
    }

    @Override
    public List<CustomerModel> getAll() {
        return ((List<CustomerData>) customerRepository.findAll())
                .stream()
                .map(transform::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        customerRepository.deleteById(id);
    }
}
