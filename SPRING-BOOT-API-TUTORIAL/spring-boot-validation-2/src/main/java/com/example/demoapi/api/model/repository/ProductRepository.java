package com.example.demoapi.api.model.repository;

import com.example.demoapi.api.model.entity.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findByNameContains(String name);
}
