package com.morben.restapi.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HouseRequestModel {

    @NotNull
    private String id;

    @NotNull
    private String name;

    @NotNull
    private String founder;

    @NotNull
    private String houseGhost;

    @NotNull
    private String mascot;
    private String school;
    private List<String> colors;
    private List<String> values;
    private String headOfHouse;
}
