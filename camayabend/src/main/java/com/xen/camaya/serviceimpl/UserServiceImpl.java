package com.xen.camaya.serviceimpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.xen.camaya.entity.UserData;
import com.xen.camaya.model.UserModel;
import com.xen.camaya.repository.UserRepository;
import com.xen.camaya.service.UserService;
import com.xen.camaya.transform.TransformUserServ;

@Service
public class UserServiceImpl extends BaseServiceImpl<UserModel, UserData, Integer> implements UserService {

    private final TransformUserServ transformUserServ;
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository, TransformUserServ transformUserServ) {
        super(userRepository);
        this.userRepository = userRepository;
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
        UserData user = userRepository.findById(userId).orElse(null);
        UserData admin = userRepository.findById(adminId).orElse(null);

        if (user == null || admin == null) return false;
        if (!"admin".equals(admin.getRole())) return false;

        user.setAdminId(adminId);
        userRepository.save(user);
        return true;
    }

    @Override
    public List<UserModel> getLinkedCustomers(Integer adminId) {
        return userRepository.findByAdminId(adminId)
            .stream()
            .map(transformUserServ::toModel)
            .toList();
    }


    

}
