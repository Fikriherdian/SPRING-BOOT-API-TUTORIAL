package com.example.demoapi.api.controller;
import com.example.demoapi.api.dto.CategoryData;
import com.example.demoapi.api.dto.ResponseData;
import com.example.demoapi.api.dto.SearchData;
import com.example.demoapi.api.model.entity.Category;
import com.example.demoapi.api.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<ResponseData<Category>> create(@Valid @RequestBody CategoryData categoryData, Errors errors){
            ResponseData<Category> responseData = new ResponseData<>();

        if(errors.hasErrors()){
            for(ObjectError error : errors.getAllErrors()){
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

//        transform DTO category ke Entity category secara otomatis menggunakan library modelMapper
        Category category = modelMapper.map(categoryData,Category.class);
        responseData.setStatus(true);
        responseData.setPayload(categoryService.save(category));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping
    public Iterable<Category> findAll(){
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Category> findOne(@PathVariable("id") Long id){
        return  categoryService.findOne(id);
    }

    @PutMapping
    public ResponseEntity<ResponseData<Category>> update(@Valid @RequestBody CategoryData categoryData, Errors errors){
        ResponseData<Category> responseData = new ResponseData<>();

        if(errors.hasErrors()){
            for(ObjectError error : errors.getAllErrors()){
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        Category category = modelMapper.map(categoryData,Category.class);
        responseData.setStatus(true);
        responseData.setPayload(categoryService.save(category));
        return ResponseEntity.ok(responseData);
    }

    @DeleteMapping("/{id}")
    public void removeOne(@PathVariable("id") Long id){
        categoryService.removeOne(id);
    }

    @GetMapping("search/{page}/{size}")
    public Iterable<Category> findByName(@RequestBody SearchData searchData, @PathVariable("page")int page, @PathVariable("size") int size){
        Pageable pageable = PageRequest.of(page,size);

        return categoryService.findByName(searchData.getSearchKey(),pageable);
    }

    @GetMapping("search/{page}/{size}/{sort}")
    public Iterable<Category> findByName(@RequestBody SearchData searchData, @PathVariable("page")int page, @PathVariable("size") int size,
                                         @PathVariable String sort){
        Pageable pageable = PageRequest.of(page,size, Sort.by("id").descending());

        if(sort.equalsIgnoreCase("desc")){
            pageable = PageRequest.of(page,size, Sort.by("id").descending());
        }

        return categoryService.findByName(searchData.getSearchKey(),pageable);
    }
}
