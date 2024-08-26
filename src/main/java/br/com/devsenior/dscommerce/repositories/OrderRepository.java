package br.com.devsenior.dscommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.devsenior.dscommerce.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{
    
}
