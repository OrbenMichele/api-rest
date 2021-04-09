package com.morben.restapi.service.impl;

import com.morben.restapi.model.request.HousesRequestModel;
import com.morben.restapi.service.remote.HouseServiceRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HouseServiceImpl {

    @Autowired
    private HouseServiceRemote houseServiceRemote;

    public HousesRequestModel getAll() {
        return houseServiceRemote.getAll();
    }

}
