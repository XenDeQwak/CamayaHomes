package com.xen.camaya.service;

import java.util.List;

import com.xen.camaya.model.PropertyModel;

public interface PropertyService extends CrudService<PropertyModel, Integer> {
    List<PropertyModel> getAll();
    PropertyModel get(Integer id);
    PropertyModel create(PropertyModel propertyModel);
    PropertyModel update(PropertyModel propertyModel);
    void delete(Integer id);
}
