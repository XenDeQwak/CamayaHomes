package com.xen.camaya.transform;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.xen.camaya.entity.PropertyData;
import com.xen.camaya.model.PropertyModel;

@Service
public class TransformPropertyServImpl implements TransformPropertyServ {

    private final ModelMapper mapper = new ModelMapper();

    @Override
    public PropertyData toEntity(PropertyModel propertyModel) {
        return mapper.map(propertyModel, PropertyData.class);
    }

    @Override
    public PropertyModel toModel(PropertyData propertyData) {
        return mapper.map(propertyData, PropertyModel.class);
    }
}
