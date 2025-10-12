package com.xen.camaya.transform;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.xen.camaya.entity.UserData;
import com.xen.camaya.model.UserModel;

@Service
public class TransformUserServImpl implements TransformUserServ {

    private final ModelMapper mapper = new ModelMapper();
    @Override
    public UserData toEntity(UserModel userModel) {
        return mapper.map(userModel, UserData.class);
    }

    @Override
    public UserModel toModel(UserData userData) {
        return mapper.map(userData, UserModel.class);
    }
    
}
