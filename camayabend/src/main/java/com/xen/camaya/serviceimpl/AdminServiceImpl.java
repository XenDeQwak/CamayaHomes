package com.xen.camaya.serviceimpl;

import org.springframework.stereotype.Service;

import com.xen.camaya.entity.AdminData;
import com.xen.camaya.model.AdminModel;
import com.xen.camaya.repository.AdminRepository;
import com.xen.camaya.service.AdminService;
import com.xen.camaya.transform.TransformAdminServ;

@Service
public class AdminServiceImpl extends BaseServiceImpl<AdminModel, AdminData, Integer> implements AdminService {
    private final TransformAdminServ transformAdminServ;

    public AdminServiceImpl(AdminRepository repository, TransformAdminServ transformAdminServ) {
        super(repository);
        this.transformAdminServ = transformAdminServ;
    }

    @Override
    protected AdminData toEntity(AdminModel model) {
        return transformAdminServ.toEntity(model);
    }

    @Override
    protected AdminModel toModel(AdminData entity) {
        return transformAdminServ.toModel(entity);
    }
}
