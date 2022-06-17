package com.company.controller;

import com.company.dto.merchant.MerchantDTO;
import com.company.service.MerchantService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/merchant")
public class MerchantController {

    private final MerchantService merchantService;

    @Autowired
    public MerchantController(MerchantService merchantService) {
        this.merchantService = merchantService;
    }

    @ApiOperation(value = "create", notes = "Method for create mer", nickname = "Dev")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/adm/create")
    public ResponseEntity<?> create(@RequestBody MerchantDTO dto){
        log.info("{ Authorization} "+dto);
        return ResponseEntity.ok(merchantService.create(dto));
    }

    @ApiOperation(value = "update", notes = "Method for update mer", nickname = "Dev")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/adm/update/{id}")
    public ResponseEntity<?> update(@RequestBody MerchantDTO dto, @PathVariable("id") String id){
        log.info("{ Authorization} "+dto);
        return ResponseEntity.ok(merchantService.update(id,dto));
    }

    @ApiOperation(value = "getById", notes = "Method for getById mer", nickname = "Dev")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/adm/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") String id){
        return ResponseEntity.ok(merchantService.getById(id));
    }

    @ApiOperation(value = "getByaddress", notes = "Method for getByaddress mer", nickname = "Dev")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/adm/getByaddress/{address}")
    public ResponseEntity<?> getByaddress(@PathVariable("address") String address){
        return ResponseEntity.ok(merchantService.getByAddress(address));
    }

    @ApiOperation(value = "delete", notes = "Method for delete mer", nickname = "Dev")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/adm/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id) {
        return ResponseEntity.ok(merchantService.delete(id));
    }
}
