package br.com.devsenior.dscommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.devsenior.dscommerce.dto.ProductDTO;
import br.com.devsenior.dscommerce.entities.Product;
import br.com.devsenior.dscommerce.repositories.ProductRepository;
import br.com.devsenior.dscommerce.services.exceptions.ResourceNotFoundException;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Transactional(readOnly = true) // lock de apenas leitura. Deixa mais rápido
    public ProductDTO findById(Long id) {
        // orElseThrow -> Tenta pegar o objeto, caso não consiga, lançará exceção.
        // Em caso de erro a camada controller irá interceptar e tratar conforme a customização
        // da classe: ControllerExceptionHandler e a classe de lançamento de exceção: ResourceNotFoundException
        Product product = repository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Recurso não encontrado"));
        return new ProductDTO(product);
    }

    @Transactional(readOnly = true) // lock de apenas leitura. Deixa mais rápido
    public Page<ProductDTO> findAll(Pageable pageable) {
        Page<Product> result = repository.findAll(pageable); // Retorna Collection Page do tipo Product
        return result.map(x -> new ProductDTO(x)); // Converte Page<Product> para Page<ProductDTO>
        // Como Page já é um stream, não precisamos convertê-lo usando stream()
    }

    @Transactional // Sem o readOnly, pois irá salvar um dado no BD.
    public ProductDTO insert(ProductDTO dto) {
        Product entity = new Product();
        copyDtoToEntity(dto, entity); // Converte DTO vindo do controle para Product
        entity = repository.save(entity); // Retornará o Objeto salvo no BD
        return new ProductDTO(entity); // Converte para DTO antes de retornar

    }

    @Transactional // Sem o readOnly, pois irá salvar um dado no BD.
    public ProductDTO update(Long id, ProductDTO dto) {
        Product entity = repository.getReferenceById(id);//Prepara o Objeto. Monitorado pela JPA.
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new ProductDTO(entity);

    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    private void copyDtoToEntity(ProductDTO dto, Product entity) {
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setImgUrl(dto.getImgUrl());
    }
    
}
