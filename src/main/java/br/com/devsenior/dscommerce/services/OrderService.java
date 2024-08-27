package br.com.devsenior.dscommerce.services;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.devsenior.dscommerce.dto.OrderDTO;
import br.com.devsenior.dscommerce.dto.OrderItemDTO;
import br.com.devsenior.dscommerce.entities.Order;
import br.com.devsenior.dscommerce.entities.OrderItem;
import br.com.devsenior.dscommerce.entities.OrderStatus;
import br.com.devsenior.dscommerce.entities.Product;
import br.com.devsenior.dscommerce.entities.User;
import br.com.devsenior.dscommerce.repositories.OrderItemRepository;
import br.com.devsenior.dscommerce.repositories.OrderRepository;
import br.com.devsenior.dscommerce.repositories.ProductRepository;
import br.com.devsenior.dscommerce.services.exceptions.ResourceNotFoundException;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @Transactional(readOnly = true) // lock de apenas leitura. Deixa mais rápido
    public OrderDTO findById(Long id) {
        // orElseThrow -> Tenta pegar o objeto, caso não consiga, lançará exceção.
        // Em caso de erro a camada controller irá interceptar e tratar conforme a customização
        // da classe: ControllerExceptionHandler e a classe de lançamento de exceção: ResourceNotFoundException
        Order order = repository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Recurso não encontrado"));
        authService.validateSelfOrAdmin(order.getClient().getId());
        return new OrderDTO(order);
    }

    @Transactional(readOnly = true)
    public OrderDTO insert(OrderDTO dto) {

        Order order = new Order();
        order.setMoment(Instant.now());
        order.setStatus(OrderStatus.WAITING_PAYMENT);
        User user = userService.authenticated();
        order.setClient(user);

        for (OrderItemDTO itemDTO : dto.getItems()) {
            Product product = productRepository.getReferenceById(itemDTO.getProductId());
            OrderItem item = new OrderItem(order, product, itemDTO.getQuantity(), product.getPrice());
            order.getItems().add(item);
        }
        repository.save(order);
        orderItemRepository.saveAll(order.getItems());

        return new OrderDTO(order);
    }

    
}
