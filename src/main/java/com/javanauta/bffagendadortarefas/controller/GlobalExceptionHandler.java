package com.javanauta.bffagendadortarefas.controller;

import com.javanauta.bffagendadortarefas.infrastructure.exception.ConflictException;
import com.javanauta.bffagendadortarefas.infrastructure.exception.ResourceNotFoundException;
import com.javanauta.bffagendadortarefas.infrastructure.exception.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Centraliza o tratamento de exceções lançadas pelos controllers.
 * Converte as exceções em respostas HTTP com mensagens e status adequados.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    // Retorna HTTP 404 quando o recurso solicitado não é encontrado.
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    // Retorna HTTP 409 quando ocorre um conflito, como um email já cadastrado.
    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<String> handleConflictException(ConflictException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    // Retorna HTTP 401 quando ocorre uma falha de autenticação.
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<String> handleUnauthorizedException(UnauthorizedException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }
}
