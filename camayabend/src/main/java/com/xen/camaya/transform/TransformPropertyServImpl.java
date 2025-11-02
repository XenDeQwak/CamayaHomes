package com.xen.camaya.transform;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.xen.camaya.entity.PropertyData;
import com.xen.camaya.entity.PropertyImageData;
import com.xen.camaya.model.PropertyModel;
import com.xen.camaya.model.PropertyImageModel;

@Service
public class TransformPropertyServImpl implements TransformPropertyServ {

    private final ModelMapper mapper = new ModelMapper();

    public TransformPropertyServImpl() {
        mapper.typeMap(PropertyData.class, PropertyModel.class).addMappings(m -> 
            m.map(PropertyData::getImages, PropertyModel::setImages)
        );
        mapper.createTypeMap(PropertyImageData.class, PropertyImageModel.class);
    }

    @Override
    public PropertyData toEntity(PropertyModel propertyModel) {
        return mapper.map(propertyModel, PropertyData.class);
    }

    @Override
    public PropertyModel toModel(PropertyData propertyData) {
        return mapper.map(propertyData, PropertyModel.class);
    }
}
