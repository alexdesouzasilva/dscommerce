package br.com.devsenior.dscommerce.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.devsenior.dscommerce.dto.ProductDTO;
import br.com.devsenior.dscommerce.services.ProductService;
import jakarta.validation.Valid;


@RestController // Respondendo pela web
@RequestMapping(value = "/products")
public class ProductController {

    //Exceções de ResourceNotFound estão sendo tratadas no pacote handlers:
    //ControllerExceptionHandler

    @Autowired
    private ProductService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id) { //@PathVariable Variável que será passada na rota/endpoint
        ProductDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<Page<ProductDTO>> findAll(
        @RequestParam(name = "name", defaultValue = "") String name, 
        Pageable pageable) { //Pageable para paginação.
        Page<ProductDTO> dto = service.findAll(name, pageable);
        return ResponseEntity.ok(dto);
    }

    //@Valid -> Irá verificar as validações inseridas nos atributos do DTO
    @PostMapping
    public ResponseEntity<ProductDTO> insert(@Valid @RequestBody ProductDTO dto) { //Corpo da requisição
        dto = service.insert(dto);
        // ** Boas práticas ao realizar um post:
        // Pega a URI corrente http://localhost:8080/products e concatena com o id e retorna para o usuário
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(dto.getId()).toUri(); 
        return ResponseEntity.created(uri).body(dto);
    }

    //@Valid -> Adicionar antes do RequestBody, pois o corpo da requisição que será analisado
    @PutMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable Long id, @Valid @RequestBody ProductDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();// 204 - sucesso sem corpo de resposta.

    }

}
