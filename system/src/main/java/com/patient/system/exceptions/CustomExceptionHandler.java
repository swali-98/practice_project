package com.patient.system.exceptions;

import com.patient.system.dtos.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(BillPendingExeception.class)
    public ResponseEntity<ErrorDto> billPendingExceptionHandler(BillPendingExeception ex){

        ErrorDto errorDto=ErrorDto.builder()
                .message(ex.getMessage())
                .errorId(1L)
                .contact("swalihbn98@gmail.com")
                .build();
        return new ResponseEntity<>(errorDto, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(PatientNotFoundException.class)
    public ResponseEntity<ErrorDto> billPendingExceptionHandler(PatientNotFoundException ex){

        ErrorDto errorDto=ErrorDto.builder()
                .message(ex.getMessage())
                .errorId(1L)
                .contact("swalihbn98@gmail.com")
                .build();
        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
    }
}
