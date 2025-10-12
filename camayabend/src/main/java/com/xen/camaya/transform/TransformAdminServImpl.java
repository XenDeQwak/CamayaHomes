package com.xen.camaya.transform;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.xen.camaya.entity.AdminData;
import com.xen.camaya.model.AdminModel;

@Service
public class TransformAdminServImpl implements TransformAdminServ{

    private final ModelMapper mapper = new ModelMapper();
    @Override
    public AdminData toEntity(AdminModel adminModel) {
        return mapper.map(adminModel, AdminData.class);
    }

    @Override
    public AdminModel toModel(AdminData adminData) {
        return mapper.map(adminData, AdminModel.class);
    }
    
}
