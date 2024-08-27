package br.com.devsenior.dscommerce.controller.handlers;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;
import br.com.devsenior.dscommerce.dto.CustomError;
import br.com.devsenior.dscommerce.dto.ValidationError;
import br.com.devsenior.dscommerce.services.exceptions.DataBaseException;
import br.com.devsenior.dscommerce.services.exceptions.ForbiddenException;
import br.com.devsenior.dscommerce.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;

// handlers -> Pacote que intercepta alguma coisa

/*
 * O Objetivo dessa classe é evitar diversos try-catch na Classe Controller 
 * tratando ID não encontrado
 */

@ControllerAdvice
public class ControllerExceptionHandler {

    //Sempre olhar a exceção lançada pelo framework e tratá-la

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
    public ResponseEntity<CustomError> dataBaseException(DataBaseException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        CustomError err = new CustomError(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
    
    // Tratamento de mensagem do Bean validation das classes DTO
    // para retornar as message descritas nas validações
    // CustomError -> Super tipo de ValidationError
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomError> methodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY; // STATUS 422
        ValidationError err = new ValidationError(Instant.now(), status.value(), "Dados inválidos", request.getRequestURI());
        //Pegando campos e mensagens de erro que adicionamos no DTO.
        for (FieldError f : e.getBindingResult().getFieldErrors()) {
           err.addError(f.getField(), f.getDefaultMessage()); 
        }
        return ResponseEntity.status(status).body(err);
    }

        //Exceção customizada para Forbidden Exception
        @ExceptionHandler(ForbiddenException.class)
        public ResponseEntity<CustomError> forbidden(ForbiddenException e, HttpServletRequest request) {
            HttpStatus status = HttpStatus.FORBIDDEN;
            CustomError err = new CustomError(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());
            return ResponseEntity.status(status).body(err);
        }
}
