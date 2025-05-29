package com.br.biosentinela.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroDTO> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> erros = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                erros.put(error.getField(), error.getDefaultMessage())
        );

        ErroDTO erroDTO = new ErroDTO("Erro de validação", erros);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroDTO);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErroDTO> handleRuntimeException(RuntimeException ex) {
        ErroDTO erroDTO = new ErroDTO("Erro interno", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erroDTO);
    }
}
