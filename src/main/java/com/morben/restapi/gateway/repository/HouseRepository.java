package com.morben.restapi.gateway.repository;

import com.morben.restapi.model.House;
import com.morben.restapi.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.UUID;

public interface HouseRepository extends JpaRepository<House, UUID>, JpaSpecificationExecutor<House> {

    List<Persona> findByHouse(String house);
}
