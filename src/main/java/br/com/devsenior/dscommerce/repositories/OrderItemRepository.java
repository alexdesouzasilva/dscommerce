package br.com.devsenior.dscommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.devsenior.dscommerce.entities.OrdemItemPK;
import br.com.devsenior.dscommerce.entities.OrderItem;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, OrdemItemPK>{
    
}
