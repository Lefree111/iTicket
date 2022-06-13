package com.company.controller;

import com.company.dto.product.ProductDTO;
import com.company.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ProductDTO dto){
        return ResponseEntity.ok(productService.create(dto));
    }


    @GetMapping("/pagination")
    public ResponseEntity<?> get(@RequestParam(value = "page", defaultValue = "0")int page,
                                 @RequestParam(value = "size", defaultValue = "4")int size){
        return ResponseEntity.ok(productService.getList(page,size));
    }



}
