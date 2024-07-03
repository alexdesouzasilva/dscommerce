package br.com.devsenior.dscommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/*
 * Classe para customizar mensagens do Bean Validation das classes DTO
 * Os campos nada mais são que os retornados na requisição:
 */

@Getter
@Setter
@AllArgsConstructor
public class FieldMessage {

    private String fieldName;
    private String message;


    
}
