package com.company.controller;

import com.company.dto.product.InformationDTO;
import com.company.service.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/information")
public class InformationController {

    private final InformationService informationService;

    @Autowired
    public InformationController(InformationService informationService) {
        this.informationService = informationService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody InformationDTO dto) {
        return ResponseEntity.ok(informationService.create(dto));
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody InformationDTO dto, @PathVariable("id") String id) {
        return ResponseEntity.ok(informationService.update(id, dto));
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") String id) {
        return ResponseEntity.ok(informationService.getById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id) {
        return ResponseEntity.ok(informationService.delete(id));
    }
}
