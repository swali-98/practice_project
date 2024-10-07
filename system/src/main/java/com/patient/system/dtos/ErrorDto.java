package com.patient.system.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorDto {

    private String message;
    private Long errorId;
    private String contact;
}
