package com.morben.restapi.model.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseModel {
    private int errorCode;
    private String message;
}
