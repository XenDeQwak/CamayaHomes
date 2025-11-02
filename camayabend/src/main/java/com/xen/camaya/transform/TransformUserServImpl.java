package com.xen.camaya.transform;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Service;
import com.xen.camaya.entity.UserData;
import com.xen.camaya.model.UserModel;
import java.util.stream.Collectors;

@Service
public class TransformUserServImpl implements TransformUserServ {

    private final ModelMapper mapper = new ModelMapper();

    public TransformUserServImpl() {
        TypeMap<UserData, UserModel> typeMap = mapper.createTypeMap(UserData.class, UserModel.class);
        typeMap.addMappings(m -> m.skip(UserModel::setLinkedProperties));
    }

    @Override
    public UserData toEntity(UserModel userModel) {
        return mapper.map(userModel, UserData.class);
    }

    @Override
    public UserModel toModel(UserData userData) {
        UserModel model = mapper.map(userData, UserModel.class);
        if (userData.getLinkedProperties() != null) {
            model.setLinkedProperties(
                userData.getLinkedProperties()
                        .stream()
                        .map(p -> p.getId())
                        .collect(Collectors.toList())
            );
        }
        return model;
    }
}
