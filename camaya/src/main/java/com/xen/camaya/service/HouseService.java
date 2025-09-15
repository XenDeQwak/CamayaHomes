package com.xen.camaya.service;

import com.xen.camaya.model.HouseModel;
import com.xen.camaya.service.BaseService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class HouseService extends BaseService<HouseModel> {
    public HouseService(
            @Value("${service.api.house.endpoint:http://localhost:8080/api/house}") String endpointUrl
    ) {
        super(endpointUrl, HouseModel.class, HouseModel[].class);
    }
}
