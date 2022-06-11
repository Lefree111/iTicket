package com.company.controller;

import com.company.dto.product.InformationDTO;
import com.company.service.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/information")
public class InformationController {

    private final InformationService informationService;

    @Autowired
    public InformationController(InformationService informationService) {
        this.informationService = informationService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody InformationDTO dto){
        return ResponseEntity.ok(informationService.create(dto));
    }








}
