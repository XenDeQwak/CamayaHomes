package com.xen.camaya.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.xen.camaya.entity.UserData;

@Repository
public interface UserRepository extends CrudRepository<UserData, Integer>{
    UserData findByEmailAndPassword(String email, String password);
}
