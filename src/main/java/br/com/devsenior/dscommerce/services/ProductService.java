package br.com.devsenior.dscommerce.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.devsenior.dscommerce.dto.ProductDTO;
import br.com.devsenior.dscommerce.entities.Product;
import br.com.devsenior.dscommerce.repositories.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Transactional(readOnly = true) // lock de apenas leitura. Deixa mais rápido
    public ProductDTO findById(Long id) {
        Product product = repository.findById(id).get(); //.get(), pois é retornado um Optional.
        return new ProductDTO(product);
    }
    
}
