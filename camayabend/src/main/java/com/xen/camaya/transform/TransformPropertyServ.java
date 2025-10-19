package com.xen.camaya.transform;

import com.xen.camaya.entity.PropertyData;
import com.xen.camaya.model.PropertyModel;

public interface TransformPropertyServ {
    PropertyData toEntity(PropertyModel propertyModel);
    PropertyModel toModel(PropertyData propertyData);
}
