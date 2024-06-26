package br.com.devsenior.dscommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.devsenior.dscommerce.dto.ProductDTO;
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

    @GetMapping
    public Page<ProductDTO> findAll(Pageable pageable) { //Paeable para paginação.
        return service.findAll(pageable);
    }

}
