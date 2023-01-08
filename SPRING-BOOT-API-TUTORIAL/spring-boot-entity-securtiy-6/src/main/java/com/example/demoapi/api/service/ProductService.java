package com.example.demoapi.api.service;

import com.example.demoapi.api.model.entity.Product;
import com.example.demoapi.api.model.entity.Supplier;
import com.example.demoapi.api.model.repository.ProductRepository;
import com.example.demoapi.api.model.repository.SupplierRepository;
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



    public void addSupplier(Supplier supplier,Long productId){
        Product product = findOne(productId);
        if(product == null){
            throw new RuntimeException("Product with id: " +productId+ "not found");
        }
        product.getSuppliers().add(supplier);
        create(product);

    }

    public Product getByName(String name){
        return productRepository.findProductByName(name);
    }

    public List<Product> getByNameLike(String name){
        return productRepository.findProductByNameLike("%"+name+"%");
    }

    public List<Product> getByCategory(Long categoryId){
        return productRepository.findByCategory(categoryId);
    }

    public Iterable<Supplier> findAllSupplier(){
        SupplierService supplier = new SupplierService();
        return supplier.findAll();
    }
}
