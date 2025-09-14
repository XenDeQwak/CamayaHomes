package com.xen.camaya.repository;

import com.xen.camaya.entity.CustomerData;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<CustomerData, Integer> {
}
