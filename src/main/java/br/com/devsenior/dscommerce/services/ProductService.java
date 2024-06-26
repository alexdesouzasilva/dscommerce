package br.com.devsenior.dscommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Transactional(readOnly = true) // lock de apenas leitura. Deixa mais rápido
    public Page<ProductDTO> findAll(Pageable pageable) {
        Page<Product> result = repository.findAll(pageable); // Retorna Objeto Page
        return result.map(x -> new ProductDTO(x)); // Converte Page<Product> para Page<ProductDTO>
        // Como Page já é um stream, não precisamos convertê-lo usando stream()
    }
    
}
