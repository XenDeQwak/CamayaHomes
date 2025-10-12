package com.xen.camaya.transform;

import com.xen.camaya.entity.UserData;
import com.xen.camaya.model.UserModel;

public interface TransformUserServ {
    UserData toEntity(UserModel userModel);
    UserModel toModel(UserData userData);
}
