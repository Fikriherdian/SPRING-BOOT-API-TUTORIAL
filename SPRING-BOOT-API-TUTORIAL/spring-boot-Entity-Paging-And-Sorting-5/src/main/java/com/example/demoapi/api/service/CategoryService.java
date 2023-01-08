package com.example.demoapi.api.service;

import com.example.demoapi.api.model.entity.Category;
import com.example.demoapi.api.model.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.TransactionScoped;
import java.util.Optional;

@Service
@TransactionScoped
public class CategoryService {


    @Autowired
    private CategoryRepository categoryRepository;

    public Category save(Category category){
        return categoryRepository.save(category);
    }

    public Optional<Category> findOne(Long id){
        Optional<Category> category = categoryRepository.findById(id);
        if(!category.isPresent()){
            return Optional.empty();
        }

        return category;
    }

    public Iterable<Category> findAll(){
        return categoryRepository.findAll();
    }

    public Category removeOne(Long id){
        categoryRepository.deleteById(id);
        return null;
    }

    public Iterable<Category> findByName(String name, Pageable pageable){
        return categoryRepository.findByNameContains(name,pageable);
    };
}
