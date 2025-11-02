package com.xen.camaya.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xen.camaya.entity.UserData;

@Repository
public interface UserRepository extends JpaRepository<UserData, Integer>{
    UserData findByEmailAndPassword(String email, String password);
    List<UserData> findByAdminId(Integer adminId);
    boolean existsByLinkedProperties(Integer linkedProperties);
}
