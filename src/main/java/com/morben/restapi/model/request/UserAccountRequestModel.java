package com.morben.restapi.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserAccountRequestModel {

    @NotNull
    private String name;

    @NotNull
    private String email;

    @NotNull
    private String cpf;

    private String phoneNumber;

    private String cellNumber;

    @NotNull
    private String login;

    @NotNull
    private String password;

    @NotNull
    private UUID profileId;
}
