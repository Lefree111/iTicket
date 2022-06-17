package com.company.controller;

import com.company.dto.profile.ProfileDTO;
import com.company.service.ProfileService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/profile")
public class ProfileController {

    private final ProfileService profileService;

    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @ApiOperation(value = "create", notes = "Method for create profile", nickname = "Dev")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/adm/create")
    public ResponseEntity<ProfileDTO> create(@RequestBody ProfileDTO dto){
        log.info("{ Authorization} "+dto);
        return ResponseEntity.ok(profileService.create(dto));
    }

    @ApiOperation(value = "pagination", notes = "Method for pagination profile", nickname = "Dev")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/adm/pagination")
    public ResponseEntity<?> get(@RequestParam(value = "page", defaultValue = "0")int page,
                                 @RequestParam(value = "size", defaultValue = "4")int size){
        return ResponseEntity.ok(profileService.getList(page,size));
    }

    @ApiOperation(value = "update", notes = "Method for update profile", nickname = "Dev")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/adm/update/{id}")
    public ResponseEntity<ProfileDTO> update(@PathVariable("id") String id,
                                             @RequestBody ProfileDTO dto){
        log.info("{ Authorization} "+dto);
        return ResponseEntity.ok(profileService.update(id, dto));
    }

    @ApiOperation(value = "delete", notes = "Method for delete profile", nickname = "Dev")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/adm/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id){
        return ResponseEntity.ok(profileService.delete(id));
    }
}
