package com.morben.restapi.service.remote;

import com.morben.restapi.model.request.HousesRequestModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(url="${potter.url}", name = "potter-house")
@RequestMapping(value = "/houses", produces = MediaType.APPLICATION_JSON_VALUE, headers = { "x-ccasset-language=pt_BR", "content-type=application/json" })
public interface HouseServiceRemote {

    @GetMapping("/")
    HousesRequestModel getAll();

}