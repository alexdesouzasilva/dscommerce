package br.com.devsenior.dscommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.devsenior.dscommerce.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
  
}