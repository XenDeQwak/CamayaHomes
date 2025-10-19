package com.xen.camaya.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xen.camaya.entity.PropertyData;

public interface PropertyRepository extends JpaRepository<PropertyData, Integer> {
    
}
