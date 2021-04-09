package com.morben.restapi.gateway.http;

import com.morben.restapi.model.request.HousesRequestModel;
import com.morben.restapi.service.impl.HouseServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("house")
@RestController
@RequestMapping("/house")
public class HouseResource {

    @Autowired
    private HouseServiceImpl houseService;

    @ApiOperation(value = "get Houses",
            notes = "Efetua a requisição de consulta em URL externa das houses cadastradas")
    @GetMapping
    public HousesRequestModel getAll() {
        return houseService.getAll();
    }

}
