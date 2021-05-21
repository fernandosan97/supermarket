package com.fernando.sinch.supermarket.repository;

import com.fernando.sinch.supermarket.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Optional<Product> findById(Integer id);
}
