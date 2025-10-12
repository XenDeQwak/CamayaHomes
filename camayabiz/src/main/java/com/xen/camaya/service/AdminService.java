package com.xen.camaya.service;

import com.xen.camaya.model.AdminModel;

import java.util.List;

public interface AdminService extends CrudService<AdminModel, Integer> {
    List<AdminModel> getAll();
    AdminModel get(Integer id);
    AdminModel create(AdminModel adminModel);
    AdminModel update(AdminModel adminModel);
    void delete(Integer id);
}
