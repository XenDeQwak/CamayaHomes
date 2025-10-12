package com.xen.camaya.repository;

import org.springframework.data.repository.CrudRepository;

import com.xen.camaya.entity.UserData;

public interface UserRepository extends CrudRepository<UserData, Integer>{}
