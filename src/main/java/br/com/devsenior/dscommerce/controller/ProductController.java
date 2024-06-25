package br.com.devsenior.dscommerce.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.devsenior.dscommerce.dto.ProductDTO;
import br.com.devsenior.dscommerce.entities.Product;
import br.com.devsenior.dscommerce.repositories.ProductRepository;
import br.com.devsenior.dscommerce.services.ProductService;

@RestController // Respondendo pela web
@RequestMapping(value = "/products")
public class ProductController {


    @Autowired
    private ProductService service;

    @GetMapping(value = "/{id}")
    public ProductDTO findById(@PathVariable Long id) { //@PathVariable Variável que será passada na rota/endpoint
        return service.findById(id);
    }

}
