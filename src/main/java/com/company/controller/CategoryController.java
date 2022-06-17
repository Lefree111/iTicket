package com.company.controller;

import com.company.dto.category.CategoryDTO;
import com.company.enums.profile.ProfileRole;
import com.company.service.CategoryService;
import com.company.util.JWTUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/api/v1/category")
@Api(tags = "category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @ApiOperation(value = "create", notes = "Method for create category", nickname = "Dev")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/adm/create")
    public ResponseEntity<?> create(@RequestBody CategoryDTO dto){
        log.info("{ Authorization} "+dto);
        return ResponseEntity.ok(categoryService.create(dto));
    }

    @ApiOperation(value = "getById", notes = "Method for getById profile", nickname = "Dev")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/adm/getById/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") String id){
        return ResponseEntity.ok(categoryService.getById(id));
    }

    @ApiOperation(value = "update", notes = "Method for update profile", nickname = "Dev")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/adm/update/{id}")
    public ResponseEntity<?> update(@RequestBody CategoryDTO dto,@PathVariable("id") String id){
        log.info("{ Authorization} "+dto);
        return ResponseEntity.ok(categoryService.update(id,dto));
    }

    @ApiOperation(value = "delete", notes = "Method for delete profile", nickname = "Dev")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/adm/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id){
        return ResponseEntity.ok(categoryService.delete(id));
    }

}