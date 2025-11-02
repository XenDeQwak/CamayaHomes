package com.xen.camaya.serviceimpl;
import org.springframework.stereotype.Service;

import com.xen.camaya.entity.PropertyData;
import com.xen.camaya.model.PropertyModel;
import com.xen.camaya.repository.PropertyRepository;
import com.xen.camaya.service.PropertyService;
import com.xen.camaya.transform.TransformPropertyServ;

@Service
public class PropertyServiceImpl extends BaseServiceImpl<PropertyModel, PropertyData, Integer> implements PropertyService {

    private final TransformPropertyServ transformPropertyServ;
    private final PropertyRepository repository;

    protected PropertyServiceImpl(PropertyRepository repository, TransformPropertyServ transformPropertyServ) {
        super(repository);
        this.repository = repository;
        this.transformPropertyServ = transformPropertyServ;
    }

    @Override
    protected PropertyData toEntity(PropertyModel propertyModel) {
        return transformPropertyServ.toEntity(propertyModel);
    }

    @Override
    protected PropertyModel toModel(PropertyData propertyData) {
        return transformPropertyServ.toModel(propertyData);
    }

}
