package com.morben.restapi.service.impl;

import com.morben.restapi.exceptions.ErrorMessages;
import com.morben.restapi.exceptions.RestApiServiceException;
import com.morben.restapi.gateway.repository.PersonaRepository;
import com.morben.restapi.model.HouseLog;
import com.morben.restapi.model.Persona;
import com.morben.restapi.model.request.HousesRequestModel;
import com.morben.restapi.model.request.PersonaRequestModel;
import com.morben.restapi.service.PersonaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Service
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    HouseServiceImpl houseService;

    @Autowired
    HouseLogServiceImpl houseLogService;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public Persona insert(PersonaRequestModel personaToInsert) {

        //Grava log para registrar o retorno recebido e o objeto criado
        //Mantém o id da house recebida para criar uma nova persona em caso de não encontrar um id correspondente
        HouseLog houseLog = new HouseLog();
        try {

            Persona persona = modelMapper.map(personaToInsert, Persona.class);
            Persona savedPersona = null;
            if (validateHouseId(houseLog, persona)) {
                savedPersona = this.save(persona);
                houseLog.setPersona(savedPersona.getId());
            }

            return savedPersona;
        }catch (Exception e){
            throw e;
        } finally {
            houseLogService.save(houseLog);
        }
    }

    @Override
    public List<Persona> findAll(int page, int limit) {
        
        if (page > 0) page--;

        Pageable pageableRequest = PageRequest.of(page, limit);
        Page<Persona> personasPage = this.personaRepository.findAll(pageableRequest);

        if (personasPage == null) throw new RestApiServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        return personasPage.getContent();
    }

    @Override
    public void deleteById(UUID id) {
        try {
            personaRepository.deleteById(id);
        }catch (Exception e){
            throw new RestApiServiceException(ErrorMessages.NO_RECORD_ID_FOUND.getErrorMessage());
        }
    }

    @Override
    public Persona update(@Valid PersonaRequestModel personaToUpdate, UUID id) {

        HouseLog houseLog = null;
        try {
            Persona personaSaved = this.findById(id);
            Persona persona = modelMapper.map(personaToUpdate, Persona.class);
            if (!personaSaved.getHouse().equals(personaToUpdate.getHouse())) {
                houseLog = new HouseLog();
                //validar se novo id_house é válido, throws exception caso não encontree id informado
               this.validateHouseId(houseLog, persona);
            }

            BeanUtils.copyProperties(persona, personaSaved, "id");
            return this.save(personaSaved);
        }catch (Exception e){
            throw new RestApiServiceException(e.getMessage());
        }finally {
            if (houseLog != null) houseLogService.save(houseLog);
        }
    }

    public Boolean validateHouseId(HouseLog houseLog, Persona personaToValidate) {

        HousesRequestModel housesRequestModel = null;
        try {
            housesRequestModel = houseService.getAll();
            housesRequestModel.getHouseRequestModel().removeIf(house -> house.getName()==null);

            if (housesRequestModel == null) {
                throw new RestApiServiceException(ErrorMessages.NO_HOUSE_FOUND.getErrorMessage());
            }

        } catch (RestApiServiceException e){
            throw e;
        } catch (Exception e){
            throw new RestApiServiceException(e.getMessage());
        }

        houseLog.setResponse(housesRequestModel.toString());
        houseLog.setRequestedId(personaToValidate.getHouse());
        houseLog.setDataHora(new java.sql.Timestamp(System.currentTimeMillis()));

        try {

            if (housesRequestModel.getHouseRequestModel().stream().filter(house -> house.getId().equals(personaToValidate.getHouse())).findAny().isPresent()){
                houseLog.setPersona(personaToValidate.getId());
                return Boolean.TRUE;
            } else {
                throw new RestApiServiceException(ErrorMessages.NO_HOUSE_ID_FOUND.getErrorMessage());
            }
        } catch (Exception e) {
            throw new RestApiServiceException(e.getMessage());
        }
    }


    private Persona save(Persona persona) {
        try {
            return personaRepository.save(persona);
        }catch (Exception e){
            throw new RestApiServiceException(e.getMessage());
        }
    }

    @Override
    public Persona findById(UUID id) {
        return personaRepository.findById(id).orElseThrow(
                () -> new RestApiServiceException(ErrorMessages.NO_RECORD_ID_FOUND.getErrorMessage()));
    }

    @Override
    public List<Persona> findByHouse(String house) {
        List<Persona> personas = personaRepository.findByHouse(house);
        if (personas.isEmpty()) throw new RestApiServiceException(ErrorMessages.NO_RECORD_ID_FOUND.getErrorMessage());
        return personas;
    }

    @Override
    public List<Persona> findByRole(String role) {

        List<Persona> personas = personaRepository.findByHouse(role);
        if (personas.isEmpty()) throw new RestApiServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        return personas;
    }

    @Override
    public List<Persona> findBySchool(String school) {

        List<Persona> personas = personaRepository.findByHouse(school);
        if (personas.isEmpty()) throw new RestApiServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        return personas;
    }

    @Override
    public List<Persona> findByPatronus(String patronus) {
        List<Persona> personas = personaRepository.findByHouse(patronus);
        if (personas.isEmpty()) throw new RestApiServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        return personas;
    }

    @Override
    public List<Persona> findByName(String name) {
        List<Persona> personas = personaRepository.findByName(name);
        if (personas.isEmpty()) throw new RestApiServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        return personas;
    }
}