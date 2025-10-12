package com.xen.camaya.service;

import java.util.List;

import com.xen.camaya.model.UserModel;

public interface UserService extends CrudService<UserModel, Integer> {
    List<UserModel> getAll();
    UserModel get(Integer id);
    UserModel create(UserModel userModel);
    UserModel update(UserModel userModel);
    void delete(Integer id);
}
