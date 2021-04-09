package com.morben.restapi.service;

import com.morben.restapi.model.Persona;
import com.morben.restapi.model.request.PersonaRequestModel;

import java.util.List;
import java.util.UUID;

public interface PersonaService {

	Persona insert(PersonaRequestModel personaRequestModel);

	public Persona findById(UUID id);

	public List<Persona> findByHouse(String house);

	public List<Persona> findByRole(String role);

	public List<Persona> findBySchool(String school);

	public List<Persona> findByPatronus(String patronus);

	public List<Persona> findByName(String name);

	public List<Persona> findAll(int page, int limit);

	public void deleteById(UUID id);

	public Persona update(PersonaRequestModel personaRequestModel, UUID id);
}