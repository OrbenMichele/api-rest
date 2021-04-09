package com.morben.restapi.gateway.repository;

import com.morben.restapi.model.Persona;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.UUID;

public interface PersonaRepository extends PagingAndSortingRepository<Persona, UUID> {

    List<Persona> findByHouse(String house);

    List<Persona> findByRole(String role);

    List<Persona> findByName(String name);

    List<Persona> findBySchool(String school);

    List<Persona> findByPatronus(String patronus);

}
