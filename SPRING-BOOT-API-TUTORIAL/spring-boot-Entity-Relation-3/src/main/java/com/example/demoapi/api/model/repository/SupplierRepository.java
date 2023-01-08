package com.example.demoapi.api.model.repository;

import com.example.demoapi.api.model.entity.Product;
import com.example.demoapi.api.model.entity.Supplier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.websocket.server.PathParam;
import java.util.List;

public interface SupplierRepository extends CrudRepository<Supplier,Long>{

Supplier findByEmail(String email);

List<Supplier> findByName(String name);

List<Supplier> findByNameContains(String name);

List<Supplier> findByNameOrEmail(String name,String email);

List<Supplier> findByNameContainsOrEmailContains(String name,String email);

}
