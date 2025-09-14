package com.xen.camaya.service;

import com.xen.camaya.model.HouseModel;

public interface HouseService {
    HouseModel[] getAll() throws Exception;
    HouseModel get(Integer id) throws Exception;
    HouseModel create(HouseModel customerModel) throws Exception;
    HouseModel update(HouseModel customerModel) throws Exception;
    void delete(Integer id) throws Exception;
}
