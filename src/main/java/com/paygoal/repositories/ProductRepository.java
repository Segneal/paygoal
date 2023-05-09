package com.paygoal.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paygoal.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
    
    List<Product> findAllByOrderByPriceAsc();
    List<Product> findAllByOrderByPriceDesc();

}
