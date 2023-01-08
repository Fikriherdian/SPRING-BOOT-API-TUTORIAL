package com.example.demoapi.api.controller;

import com.example.demoapi.api.dto.ResponseData;
import com.example.demoapi.api.dto.SearchData;
import com.example.demoapi.api.dto.SupplierData;
import com.example.demoapi.api.model.entity.Supplier;
import com.example.demoapi.api.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/supplier")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @PostMapping
    public ResponseEntity<ResponseData<Supplier>> create(@Valid @RequestBody SupplierData supplierData, Errors errors){
        ResponseData<Supplier> responseData = new ResponseData<>();
        if(errors.hasErrors()){
            for(ObjectError error : errors.getAllErrors()){
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        //tranform supplierDTO ke entity suplier manual
        Supplier supplier = new Supplier();
        supplier.setName(supplierData.getName());
        supplier.setAddress(supplierData.getAddress());
        supplier.setEmail(supplierData.getEmail());

        responseData.setStatus(true);
        responseData.setPayload(supplierService.save(supplier));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping
    public Iterable<Supplier> findAll(){
        return supplierService.findAll();
    }

    @GetMapping("/{id}")
    public Supplier findOne(@PathVariable("id") Long id){
        return supplierService.findOne(id);
    }

    @PutMapping
    public ResponseEntity<ResponseData<Supplier>> update(@Valid @RequestBody SupplierData supplierData, Errors errors){
        ResponseData<Supplier> responseData = new ResponseData<>();
        if(errors.hasErrors()){
            for(ObjectError error : errors.getAllErrors()){
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        //tranform  dto supplier ke entity supplier manual
        Supplier supplier = new Supplier();
        supplier.setName(supplierData.getName());
        supplier.setAddress(supplierData.getAddress());
        supplier.setEmail(supplierData.getEmail());

        responseData.setStatus(true);
        responseData.setPayload(supplierService.save(supplier));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping("/search/email")
    public Supplier findByEmail(@RequestBody SearchData searchData){
        return supplierService.findByEmail(searchData.getSearchKey());
    }

    @GetMapping("search/name")
    public List<Supplier> findByName(@RequestBody SearchData searchData){
        return supplierService.findByName(searchData.getSearchKey());
    }

    @GetMapping("search/namecontains")
    public List<Supplier> findByNameContains(@RequestBody SearchData searchData){
        return supplierService.findByNameContains(searchData.getSearchKey());
    }

    @GetMapping("search/nameoremail")
    public List<Supplier> findByNameOrEmail(@RequestBody SearchData searchData){
        return supplierService.findByNameOrEmail(searchData.getSearchKey(), searchData.getSearchKey2() );
    }

    @GetMapping("search/nameoremailcontains")
    public List<Supplier> findByNameContainsOrEmailContains(@RequestBody SearchData searchData){
        return supplierService.findByNameContainsOrEmailContains(searchData.getSearchKey(), searchData.getSearchKey2() );
    }

}
