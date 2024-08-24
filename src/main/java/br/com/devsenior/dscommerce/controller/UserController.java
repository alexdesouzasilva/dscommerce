package br.com.devsenior.dscommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.devsenior.dscommerce.dto.UserDTO;
import br.com.devsenior.dscommerce.services.UserService;


@RestController // Respondendo pela web
@RequestMapping(value = "/users")
public class UserController {

    //Exceções de ResourceNotFound estão sendo tratadas no pacote handlers:
    //ControllerExceptionHandler

    @Autowired
    private UserService service;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
    @GetMapping(value = "/me")
    public ResponseEntity<UserDTO> getMe() {
        UserDTO dto = service.getMe();
        return ResponseEntity.ok(dto);
    }

}