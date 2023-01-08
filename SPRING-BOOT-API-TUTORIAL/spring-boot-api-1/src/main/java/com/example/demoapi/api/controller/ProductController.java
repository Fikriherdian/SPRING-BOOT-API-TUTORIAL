package com.example.demoapi.api.controller;

import com.example.demoapi.api.model.entity.Product;
import com.example.demoapi.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping
    public Product create(@RequestBody Product product){
    return productService.create(product);
    }

    @GetMapping
    public Iterable<Product> list(){
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public Product findById(@PathVariable("id") Long id){
        return  productService.findOne(id);
    }

    @PutMapping
    public Product update(@RequestBody Product product){
        return productService.create(product);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
         productService.deleteById(id);
    }
}
