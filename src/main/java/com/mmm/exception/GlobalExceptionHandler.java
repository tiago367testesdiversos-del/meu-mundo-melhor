package com.mmm.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<?>handler(ResponseStatusException ex){
        return ResponseEntity
                .status(ex.getStatusCode())
                .body(ex.getReason());
    }


}

//Porque essa classe:
//
//não é controller
//não é service
//não é model
//não é repository
//
// uma camada global de tratamento de erro
//controlar TODOS os erros da sua API de forma centralizada

//HttpStatus.FORBIDDEN (403)
//HttpStatus.BAD_REQUEST (400)
//HttpStatus.NOT_FOUND (404)