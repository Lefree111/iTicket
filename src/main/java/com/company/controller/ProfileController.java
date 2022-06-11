package com.company.controller;

import com.company.dto.profile.ProfileDTO;
import com.company.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/profile")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @PostMapping("/create")
    public ResponseEntity<ProfileDTO> create(@RequestBody ProfileDTO dto){
        return ResponseEntity.ok(profileService.create(dto));
    }

    @GetMapping("/pagination")
    public ResponseEntity<?> get(@RequestParam(value = "page", defaultValue = "0")int page,
                                 @RequestParam(value = "size", defaultValue = "4")int size){
        return ResponseEntity.ok(profileService.getList(page,size));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProfileDTO> update(@PathVariable("id") String id,
                                             @RequestBody ProfileDTO dto){
        return ResponseEntity.ok(profileService.update(id, dto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id){
        return ResponseEntity.ok(profileService.delete(id));
    }
}
