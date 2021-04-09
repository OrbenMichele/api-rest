package com.morben.restapi.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonaRequestModel {

    @NotNull
    @ApiModelProperty(
            value = "Nome do personagem",
            name = "name",
            dataType = "String",
            example = "Harry Potter",
            required = true)
    private String name;

    @NotNull
    @ApiModelProperty(
            value = "Nome da função",
            name = "role",
            dataType = "String",
            example = "student",
            required = true)
    private String role;

    @NotNull
    @ApiModelProperty(
            value = "Nome da Escola",
            name = "school",
            dataType = "String",
            example = "Hogwarts School of Witchcraft and Wizardry",
            required = true)
    private String school;

    @NotNull
    @ApiModelProperty(
            value = "ID da casa",
            name = "house",
            dataType = "String",
            example = "1760529f-6d51-4cb1-bcb1-25087fce5bde",
            required = true)
    private String house;


    @NotNull
    @ApiModelProperty(
            value = "Nome do patrono",
            name = "patronus",
            dataType = "String",
            example = "stag",
            required = true)
    private String patronus;

}
