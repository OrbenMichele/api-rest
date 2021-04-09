package com.morben.restapi.service.impl;

import com.morben.restapi.gateway.repository.HouseLogRepository;
import com.morben.restapi.model.HouseLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HouseLogServiceImpl {

    @Autowired
    private HouseLogRepository houseLogRepository;

    public HouseLog save(HouseLog houseLog) {
        return houseLogRepository.save(houseLog);
    }

}
