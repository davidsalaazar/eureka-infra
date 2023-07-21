package com.salazar.security.domain.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class ApiResponse {
    private Boolean isValid;
    private String error;
}