package com.example.demoapi.api.service;

import com.example.demoapi.api.model.entity.Supplier;
import com.example.demoapi.api.model.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SupplierService {

    @Autowired
    SupplierRepository supplierRepository;

    public Supplier save(Supplier supplier){
        return supplierRepository.save(supplier);
    }

    public Supplier findOne(Long id){
        Optional<Supplier> supplier = supplierRepository.findById(id);

        if(!supplier.isPresent()){
            return  null;
        }
        return supplier.get();
    }

    public Iterable<Supplier> findAll(){
        return supplierRepository.findAll();
    }

    public void removeOne(Long id){
        supplierRepository.deleteById(id);
    }


    public Supplier findByEmail(String email){
        return supplierRepository.findByEmail(email);
    }

}
