package br.com.devsenior.dscommerce.controller.handlers;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.devsenior.dscommerce.dto.CustomError;
import br.com.devsenior.dscommerce.services.exceptions.DataBaseException;
import br.com.devsenior.dscommerce.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;

// handlers -> Pacote que intercepta alguma coisa

/*
 * O Objetivo dessa classe é evitar diversos try-catch na Classe Controller 
 * tratando ID não encontrado
 */

@ControllerAdvice
public class ControllerExceptionHandler {

    /*
     * Método para tratar erro 404 - Not Found - De forma customizada
     * HttpServletRequest -> Usado para obter os dados da requisição
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        CustomError err = new CustomError(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    //Exceção customizada para violação de integridade do Banco de dados
    @ExceptionHandler(DataBaseException.class)
    public ResponseEntity<CustomError> DataBaseException(DataBaseException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        CustomError err = new CustomError(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
    
}
