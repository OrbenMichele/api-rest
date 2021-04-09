package com.morben.restapi.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HousesRequestModel {

    @JsonProperty("houses")
    private List<HouseRequestModel> houseRequestModel;

    @Override
    public String toString() {
        return "HousesRequestModel{" +
                "houseRequestModel=" + houseRequestModel.toString() +
                '}';
    }
}
