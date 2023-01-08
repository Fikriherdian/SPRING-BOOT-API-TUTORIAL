package com.example.demoapi.api.service;

import com.example.demoapi.api.model.entity.Product;
import com.example.demoapi.api.model.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public Product create(Product product){
        return  productRepository.save(product);
    }

    public Product findOne(Long id){
        return  productRepository.findById(id).get();
    }

    public Iterable<Product> findAll(){
        return productRepository.findAll();
    }

    public void deleteById(Long id){
        productRepository.deleteById(id);
    }

    public List<Product> findByName(String name){
        return productRepository.findByNameContains(name);
    }


}
