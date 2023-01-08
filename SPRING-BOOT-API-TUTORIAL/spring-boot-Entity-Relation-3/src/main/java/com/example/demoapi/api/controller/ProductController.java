package com.example.demoapi.api.controller;

import com.example.demoapi.api.dto.ResponseData;
import com.example.demoapi.api.dto.SearchData;
import com.example.demoapi.api.model.entity.Product;
import com.example.demoapi.api.model.entity.Supplier;
import com.example.demoapi.api.service.ProductService;
import com.example.demoapi.api.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping
    public ResponseEntity<ResponseData<Product>> create(@Valid @RequestBody Product product, Errors errors){

        ResponseData<Product> responseData = new ResponseData<>();

        if(errors.hasErrors()){
           for(ObjectError error : errors.getAllErrors()){
               responseData.getMessages().add(error.getDefaultMessage());
           }
           responseData.setStatus(false);
           responseData.setPayload(null);
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        responseData.setStatus(true);
        responseData.setPayload(productService.create(product));

        return  ResponseEntity.ok(responseData);
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
    public ResponseEntity<ResponseData<Product>> update(@Valid@RequestBody Product product,Errors errors){

        ResponseData<Product> responseData = new ResponseData<>();

        if(errors.hasErrors()){
            for(ObjectError error : errors.getAllErrors()){
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        responseData.setStatus(true);
        responseData.setPayload(productService.create(product));

        return  ResponseEntity.ok(responseData);

    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
         productService.deleteById(id);
    }

    @PostMapping("/{id}")
    public void addSupplier(@RequestBody Supplier supplier,@PathVariable Long id){
        productService.addSupplier(supplier, id);
    }

    @PostMapping("/search/name")
    public Product searchByName(@RequestBody SearchData searchData){
        return productService.getByName(searchData.getSearchKey());
    }

    @PostMapping("/search/like")
    public List<Product> searchByNameLike(@RequestBody SearchData searchData){
        return productService.getByNameLike(searchData.getSearchKey());
    }

    @GetMapping("/search/category/{categoryId}")
    public List<Product> getByCategory(@PathVariable("categoryId") Long categoryId){
        return productService.getByCategory(categoryId);
    }

    @GetMapping("/search/supplier")
    public Iterable<Supplier> getBySupplier(){
        return productService.findAllSupplier();
    }
}
