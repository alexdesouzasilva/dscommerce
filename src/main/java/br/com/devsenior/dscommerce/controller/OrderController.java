package br.com.devsenior.dscommerce.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.devsenior.dscommerce.dto.OrderDTO;
import br.com.devsenior.dscommerce.dto.ProductDTO;
import br.com.devsenior.dscommerce.services.OrderService;
import jakarta.validation.Valid;


@RestController // Respondendo pela web
@RequestMapping(value = "/orders")
public class OrderController {

    //Exceções de ResourceNotFound estão sendo tratadas no pacote handlers:
    //ControllerExceptionHandler

    @Autowired
    private OrderService service;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<OrderDTO> findById(@PathVariable Long id) { //@PathVariable Variável que será passada na rota/endpoint
        OrderDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PreAuthorize("hasRole('ROLE_CLIENT')")// Qualquer usuário LOGADO poderá fazer um pedido
    @PostMapping //@Valid -> Irá verificar as validações inseridas nos atributos do DTO
    public ResponseEntity<OrderDTO> insert(@Valid @RequestBody OrderDTO dto) { //Corpo da requisição
        dto = service.insert(dto);
        // ** Boas práticas ao realizar um post:
        // Pega a URI corrente http://localhost:8080/products e concatena com o id e retorna para o usuário
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(dto.getId()).toUri(); 
        return ResponseEntity.created(uri).body(dto);
    }

}