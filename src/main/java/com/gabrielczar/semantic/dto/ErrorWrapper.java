package com.gabrielczar.semantic.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorWrapper {
    private String field;
    private String error;

}
