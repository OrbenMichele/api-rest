package com.morben.restapi.service.impl;

import com.morben.restapi.gateway.repository.PersonaRepository;
import com.morben.restapi.model.HouseLog;
import com.morben.restapi.model.Persona;
import com.morben.restapi.model.request.PersonaRequestModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
@SpringBootTest
@DisplayName("PersonServiceImpl deveria")
public class PersonaServiceImplTest {

    @InjectMocks
    @Spy
    PersonaServiceImpl inTest;

    @Mock
    ModelMapper modelMapper;

    @Mock
    HouseLogServiceImpl houseLogService;

    @Mock
    PersonaRepository personaRepository;

    @BeforeEach
    public void setUp() throws Exception {
        inTest = Mockito.spy(PersonaServiceImpl.class);
        MockitoAnnotations.initMocks(this);
    }

    @Nested
    @DisplayName("Insert Method")
    class insert{

        PersonaRequestModel personaToInsert;
        Persona persona;

        @BeforeEach
        public void setUp() {

            personaToInsert = new PersonaRequestModel();
            persona = new Persona();
            persona.setId(UUID.randomUUID());
            when(modelMapper.map(personaToInsert, Persona.class)).thenReturn(persona);
            doReturn(true).when(inTest).validateHouseId(any(HouseLog.class),eq(persona));
            when(personaRepository.save(persona)).thenReturn(persona);
        }

        @Test
        @DisplayName("given Valid Persona when insert then Call validateHouseId")
        public void givenValidPersona_whenInsert_thenCallValidateHouseId() {

            inTest.insert(personaToInsert);
            verify(inTest, times(1)).validateHouseId(any(HouseLog.class),eq(persona));
        }


        @Test
        @DisplayName("given Valid Persona when insert then Call Save Method")
        public void givenValidPersona_whenInsert_thenCallSaveMethod() {

            inTest.insert(personaToInsert);
            verify(personaRepository, times(1)).save(persona);
        }


        @Test
        @DisplayName("given Valid Persona when insert then Model Mapper")
        public void givenValidPersona_whenInsert_thenDoModelMapper() {

            inTest.insert(personaToInsert);
            verify(modelMapper, times(1)).map(personaToInsert,Persona.class);
        }

        @Test
        @DisplayName("given Valid Persona when insert then Call HouseLog Save")
        public void givenValidPersona_whenInsert_thenCallHouseLogSave() {

            inTest.insert(personaToInsert);
            verify(houseLogService, times(1)).save(any(HouseLog.class));
        }

    }

    @Nested
    @DisplayName("Delete Method")
    class delete {

        UUID uuid;

        @BeforeEach
        public void setUp() {
            uuid = UUID.randomUUID();
        }

        @Test
        @DisplayName("given Valid Persona ID when delete then Call Persona Repository DeleteById Method")
        public void givenValidPersonaID_whenDelete_thenCallPersonaRepositoryDeleteByIdMethod() {

            inTest.deleteById(uuid);
            verify(personaRepository, times(1)).deleteById(uuid);
        }
    }


    @Nested
    @DisplayName("Update Method")
    class update{

        PersonaRequestModel personaToUpdate;
        Persona persona;
        UUID uuid;

        @BeforeEach
        public void setUp() {

            uuid = UUID.randomUUID();
            persona = new Persona();
            personaToUpdate = new PersonaRequestModel();
            persona.setId(uuid);
            persona.setHouse(UUID.randomUUID().toString());
            when(modelMapper.map(personaToUpdate, Persona.class)).thenReturn(persona);
            doReturn(true).when(inTest).validateHouseId(any(HouseLog.class),eq(persona));
            doReturn(persona).when(inTest).findById(uuid);
        }

        @Test
        @DisplayName("given Valid Persona when update then Call validateHouseId")
        public void givenValidPersona_whenUpdate_thenCallValidateHouseId() {

            inTest.update(personaToUpdate, uuid);
            verify(inTest, times(1)).validateHouseId(any(HouseLog.class),eq(persona));
        }


        @Test
        @DisplayName("given Valid Persona when update then Call Update Method")
        public void givenValidPersona_whenUpdate_thenCallUpdateMethod() {

            inTest.update(personaToUpdate, uuid);
            verify(inTest, times(1)).update(personaToUpdate,uuid);
        }


        @Test
        @DisplayName("given Valid Persona when update then Model Mapper")
        public void givenValidPersona_whenUpdate_thenDoModelMapper() {

            inTest.update(personaToUpdate,uuid);
            verify(modelMapper, times(1)).map(personaToUpdate,Persona.class);
        }

        @Test
        @DisplayName("given Valid Persona when update then Call HouseLog Save")
        public void givenValidPersona_whenUpdate_thenCallHouseLogSave() {

            inTest.update(personaToUpdate, uuid);
            verify(houseLogService, times(1)).save(any(HouseLog.class));
        }

    }
    @Nested
    @DisplayName("findAll Method")
    class findAll {

        int page, limit;
        Page<Persona> personasPage;

        @BeforeEach
        public void setUp() {
            page = 0;
            limit = 10;
            personasPage = new PageImpl<Persona>(new ArrayList<>());
            doReturn(personasPage).when(personaRepository).findAll(any(Pageable.class));
        }

        @Test
        @DisplayName("given Method Call when findAll then Call Persona Repository findAll Method")
        public void givenMethodCall_whenFindAll_thenCallPersonaRepositoryFindAllMethod() {

            inTest.findAll(page,limit);
            verify(inTest, times(1)).findAll(page, limit);
        }
    }


}