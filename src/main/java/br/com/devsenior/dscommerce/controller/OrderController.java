package br.com.devsenior.dscommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.devsenior.dscommerce.dto.OrderDTO;
import br.com.devsenior.dscommerce.services.OrderService;


@RestController // Respondendo pela web
@RequestMapping(value = "/orders")
public class OrderController {

    //Exceções de ResourceNotFound estão sendo tratadas no pacote handlers:
    //ControllerExceptionHandler

    @Autowired
    private OrderService service;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<OrderDTO> findById(@PathVariable Long id) { //@PathVariable Variável que será passada na rota/endpoint
        OrderDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

}