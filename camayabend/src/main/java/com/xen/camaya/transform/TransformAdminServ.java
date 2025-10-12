package com.xen.camaya.transform;

import com.xen.camaya.entity.AdminData;
import com.xen.camaya.model.AdminModel;

public interface TransformAdminServ {
    AdminData toEntity(AdminModel adminModel);
    AdminModel toModel(AdminData adminData);
}
