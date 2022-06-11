package com.company.controller;

import com.company.dto.category.CategoryDTO;
import com.company.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CategoryDTO dto){
        return ResponseEntity.ok(categoryService.create(dto));
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") String id){
        return ResponseEntity.ok(categoryService.getById(id));
    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> update(@RequestBody CategoryDTO dto,@PathVariable("id") String id){
        return ResponseEntity.ok(categoryService.update(id,dto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id){
        return ResponseEntity.ok(categoryService.delete(id));
    }

}