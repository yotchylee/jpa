package com.example.jpa.repository;

import com.example.jpa.entity.Product;
import com.example.jpa.entity.WrappedEntity.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
  Products findAllByDescriptionContaining(String text);
}