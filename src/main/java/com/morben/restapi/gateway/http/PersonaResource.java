package com.morben.restapi.gateway.http;

import com.morben.restapi.model.Persona;
import com.morben.restapi.model.request.PersonaRequestModel;
import com.morben.restapi.service.impl.PersonaServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Api("persona")
@RestController
@RequestMapping("/persona")
public class PersonaResource {

    private static final String HOUSES_KEY = "houses";
    private static final String HOUSE_KEY = "headOfHouse";

    @Autowired
    PersonaServiceImpl personaService;

    @ApiOperation(value = "Inserir novo personagem",
            notes = "Retorna personagem criado")
    @PostMapping
    public Persona insert(@RequestBody @Valid PersonaRequestModel personaToInsert) throws Exception {
        return personaService.insert(personaToInsert);
    }

    @ApiOperation(value = "Consultar personagem pelo ID",
            notes = "Retorna personagem que possui o ID informado na busca")
    @GetMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Persona findById(@PathVariable UUID id) {
        return personaService.findById(id);
    }

    @ApiOperation(value = "Consultar todos os personagens",
            notes = "Retorna todos os personagem que possuem cadastro")
    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Persona> findAll(
                                    @ApiParam(
                                            name = "pageNumber",
                                            type = "integer",
                                            value = "Número da página de consulta",
                                            example = "0",
                                            defaultValue = "0",
                                            required = false)
                                    @RequestParam(value="pageNumber", defaultValue="0") int pageNumber,
                                    @ApiParam(
                                            name = "recordsLimit",
                                            type = "integer",
                                            value = "Número máximo de registros por página",
                                            example = "0",
                                            defaultValue = "10",
                                            required = false)
                                    @RequestParam(value="recordsLimit", defaultValue="10") int recordsLimit) {

        return personaService.findAll(pageNumber, recordsLimit);
    }

    @ApiOperation(value = "Consultar personagem por filtro",
            notes = "Retorna personagens que possuem corrêspondência ao filtro informado")
    @GetMapping(value = "/filter",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Persona> findByHouse(
                                        @ApiParam(
                                                name = "houseId",
                                                type = "String",
                                                value = "Código de identificação da house do personagem",
                                                example = "1760529f-6d51-4cb1-bcb1-25087fce5bde",
                                                required = false)
                                        @RequestParam(value = "houseId", required = false) String houseId,
                                        @ApiParam(
                                             name = "pageNumber",
                                             type = "integer",
                                             value = "Número da página de consulta",
                                             example = "0",
                                             defaultValue = "0",
                                             required = false)
                                        @RequestParam(value="pageNumber", defaultValue="0") int pageNumber,
                                        @ApiParam(
                                             name = "recordsLimit",
                                             type = "integer",
                                             value = "Número máximo de registros por página",
                                             example = "0",
                                             defaultValue = "10",
                                             required = false)
                                        @RequestParam(value="recordsLimit", defaultValue="10") int recordsLimit) {

        if (houseId != null) {
            return personaService.findByHouse(houseId);
        } else {
            return personaService.findAll(pageNumber, recordsLimit);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        personaService.deleteById(id);
    }

    @PutMapping("/{id}")
    public Persona update(@PathVariable UUID id, @RequestBody @Valid PersonaRequestModel personaToUpdate) {
        return personaService.update(personaToUpdate, id);
    }

}
