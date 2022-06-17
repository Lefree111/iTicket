package com.company.controller;

import com.company.dto.product.InformationDTO;
import com.company.enums.profile.ProfileRole;
import com.company.service.InformationService;
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
@RequestMapping("/api/v1/information")
@Api(tags = "information")
public class InformationController {

    private final InformationService informationService;

    @Autowired
    public InformationController(InformationService informationService) {
        this.informationService = informationService;
    }

    @ApiOperation(value = "create", notes = "Method for create inf", nickname = "Dev")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/adm/create")
    public ResponseEntity<?> create(@RequestBody InformationDTO dto) {
        log.info("{ Authorization} "+dto);
        return ResponseEntity.ok(informationService.create(dto));
    }

    @ApiOperation(value = "update", notes = "Method for update inf", nickname = "Dev")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/adm/update/{id}")
    public ResponseEntity<?> update(@RequestBody InformationDTO dto, @PathVariable("id") String id) {
        log.info("{ Authorization} "+dto);
        return ResponseEntity.ok(informationService.update(id, dto));
    }

    @ApiOperation(value = "getById", notes = "Method for getById inf", nickname = "Dev")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/adm/getById/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") String id) {
        return ResponseEntity.ok(informationService.getById(id));
    }

    @ApiOperation(value = "delete", notes = "Method for delete inf", nickname = "Dev")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/adm/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id) {
        return ResponseEntity.ok(informationService.delete(id));
    }
}
