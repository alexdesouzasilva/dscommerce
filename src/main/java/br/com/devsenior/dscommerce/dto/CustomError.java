package br.com.devsenior.dscommerce.dto;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Getter;


/*
 * Response Default de Erro do Spring para Recurso Não Encontrado:
 * {
    "timestamp": "2024-06-29T08:32:00.369+00:00",
    "status": 500,
    "error": "Internal Server Error",
    "path": "/products/26"
    }
 */

@Getter // Somente Getters para exigir que as informações sejam passadas pelo Construtor
@AllArgsConstructor
public class CustomError {

    //Atributos para customizar informações lançadas nas exceções.

    private Instant timestamp;
    private Integer status;
    private String error;
    private String path;
    
}
