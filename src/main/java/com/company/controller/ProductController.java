package com.company.controller;

import com.company.dto.product.ProductDTO;
import com.company.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/product")
@Api(tags = "product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @ApiOperation(value = "create", notes = "Method for create product", nickname = "Dev")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/adm/create")
    public ResponseEntity<?> create(@RequestBody ProductDTO dto) {
        log.info("{ Authorization} "+dto);
        return ResponseEntity.ok(productService.create(dto));
    }

    @ApiOperation(value = "update", notes = "Method for update product", nickname = "Dev")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/adm/update/{id}")
    public ResponseEntity<?> update(@RequestBody ProductDTO dto, @PathVariable("id") String id) {
        log.info("{ Authorization} "+dto);
        return ResponseEntity.ok(productService.update(dto, id));
    }

    @ApiOperation(value = "getById", notes = "Method for getById product", nickname = "Dev")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/adm/getById/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") String id) {
        return ResponseEntity.ok(productService.getById(id));
    }

    @ApiOperation(value = "delete", notes = "Method for delete product", nickname = "Dev")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/adm/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id) {
        return ResponseEntity.ok(productService.delete(id));
    }

    @ApiOperation(value = "pagination", notes = "Method for pagination product", nickname = "Dev")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/adm/pagination")
    public ResponseEntity<?> pagination(@RequestParam(value = "page", defaultValue = "0") int page,
                                 @RequestParam(value = "size", defaultValue = "4") int size) {
        return ResponseEntity.ok(productService.getList(page, size));
    }

}
