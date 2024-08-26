package br.com.devsenior.dscommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.devsenior.dscommerce.dto.OrderDTO;
import br.com.devsenior.dscommerce.entities.Order;
import br.com.devsenior.dscommerce.repositories.OrderRepository;
import br.com.devsenior.dscommerce.services.exceptions.ResourceNotFoundException;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Transactional(readOnly = true) // lock de apenas leitura. Deixa mais rápido
    public OrderDTO findById(Long id) {
        // orElseThrow -> Tenta pegar o objeto, caso não consiga, lançará exceção.
        // Em caso de erro a camada controller irá interceptar e tratar conforme a customização
        // da classe: ControllerExceptionHandler e a classe de lançamento de exceção: ResourceNotFoundException
        Order order = repository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Recurso não encontrado"));
        return new OrderDTO(order);
    }

    
}
