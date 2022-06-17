package com.company.controller;

import com.company.dto.attach.AttachDTO;
import com.company.service.AttachService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("/api/v1/attach")
@Api(tags = "attach")
public class AttachController {

    private final AttachService attachService;

    @Autowired
    public AttachController(AttachService attachService) {
        this.attachService = attachService;
    }

    @ApiOperation(value = "upload", notes = "Method for upload file", nickname = "Dev")
    @PreAuthorize("hasAnyRole('ADMIN, USER')")
    @PostMapping("/upload")
    public ResponseEntity<AttachDTO> create(@RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(attachService.upload(file));
    }

    @ApiOperation(value = "open", notes = "Method for open file", nickname = "Dev")
    @PreAuthorize("hasAnyRole('ADMIN, USER')")
    @GetMapping(value = "/open/{fileName}", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] open(@PathVariable("fileName") String fileName) {
        return attachService.open(fileName);
    }

    @ApiOperation(value = "open_general", notes = "Method for open_general file", nickname = "Dev")
    @PreAuthorize("hasAnyRole('ADMIN, USER')")
    @GetMapping(value = "/open_general/{fileName}", produces = MediaType.ALL_VALUE)
    public byte[] open_general(@PathVariable("fileName") String fileName) {
        return attachService.open_general(fileName);
    }

    @ApiOperation(value = "download", notes = "Method for download file", nickname = "Dev")
    @PreAuthorize("hasAnyRole('ADMIN, USER')")
    @GetMapping("/download/{fileName}")
    public ResponseEntity<Resource> download(@PathVariable("fileName") String fileName) {
        return attachService.download(fileName);
    }

    @ApiOperation(value = "list", notes = "Method for list file", nickname = "Dev")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/list")
    public ResponseEntity<?> list(@RequestParam(value = "page", defaultValue = "0") int page,
                                  @RequestParam(value = "size", defaultValue = "3") int size) {
        return ResponseEntity.ok(attachService.paginationList(page, size));
    }

    @ApiOperation(value = "delete", notes = "Method for delete file", nickname = "Dev")
    @PreAuthorize("hasAnyRole('ADMIN, USER')")
    @DeleteMapping("/delete/{key}")
    public ResponseEntity<?> delete(@PathVariable("key") String key) {
        return ResponseEntity.ok(attachService.delete(key));
    }

}
