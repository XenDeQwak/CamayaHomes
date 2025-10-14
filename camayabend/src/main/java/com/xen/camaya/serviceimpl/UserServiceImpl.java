package com.xen.camaya.serviceimpl;

import org.springframework.stereotype.Service;

import com.xen.camaya.entity.UserData;
import com.xen.camaya.model.UserModel;
import com.xen.camaya.repository.UserRepository;
import com.xen.camaya.service.UserService;
import com.xen.camaya.transform.TransformUserServ;

@Service
public class UserServiceImpl extends BaseServiceImpl<UserModel, UserData, Integer> implements UserService {

    private final TransformUserServ transformUserServ;

    public UserServiceImpl(UserRepository repository, TransformUserServ transformUserServ) {
        super(repository);
        this.transformUserServ = transformUserServ;
    }

    @Override
    protected UserData toEntity(UserModel userModel) {
        return transformUserServ.toEntity(userModel);
    }

    @Override
    protected UserModel toModel(UserData userData) {
        return transformUserServ.toModel(userData);
    }

    @Override
    public boolean assign(Integer userId, Integer adminId) {
        UserData user = repository.findById(userId).orElse(null);
        UserData admin = repository.findById(adminId).orElse(null);

        if (user == null || admin == null) return false;

        user.setAdminId(adminId);
        repository.save(user);
        return true;
    }

    

}
