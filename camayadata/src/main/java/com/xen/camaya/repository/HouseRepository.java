package com.xen.camaya.repository;

import com.xen.camaya.entity.HouseData;
import org.springframework.data.repository.CrudRepository;

public interface HouseRepository extends CrudRepository<HouseData, Integer> {
}
