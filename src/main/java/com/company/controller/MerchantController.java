package com.company.controller;

import com.company.dto.merchant.MerchantDTO;
import com.company.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/merchant")
public class MerchantController {

    private final MerchantService merchantService;

    @Autowired
    public MerchantController(MerchantService merchantService) {
        this.merchantService = merchantService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody MerchantDTO dto){
        return ResponseEntity.ok(merchantService.create(dto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody MerchantDTO dto, @PathVariable("id") String id){
        return ResponseEntity.ok(merchantService.update(id,dto));
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") String id){
        return ResponseEntity.ok(merchantService.getById(id));
    }

    @GetMapping("/getByAddress/{address}")
    public ResponseEntity<?> getByaddress(@PathVariable("address") String address){
        return ResponseEntity.ok(merchantService.getByAddress(address));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id) {
        return ResponseEntity.ok(merchantService.delete(id));
    }
}
