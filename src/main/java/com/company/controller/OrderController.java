package com.company.controller;

import com.company.dto.order.OrderDTO;
import com.company.enums.profile.ProfileRole;
import com.company.service.OrderService;
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
@RequestMapping("/api/v1/order")
@Api(tags = "order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @ApiOperation(value = "create", notes = "Method for create order", nickname = "Dev")
    @PreAuthorize("hasAnyRole('ADMIN, USER')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody OrderDTO dto) {
        log.info("{ Authorization} "+dto);
        return ResponseEntity.ok(orderService.create(dto));
    }

    @ApiOperation(value = "getList", notes = "Method for getList order", nickname = "Dev")
    @PreAuthorize("hasAnyRole('ADMIN, USER')")
    @GetMapping("/getList")
    public ResponseEntity<?> getList(@RequestParam(value = "page", defaultValue = "0") int page,
                                     @RequestParam(value = "size", defaultValue = "5") int size){
        return ResponseEntity.ok(orderService.getList(page, size));
    }

    @ApiOperation(value = "getProfileOrderList", notes = "Method for getProfileOrderList order", nickname = "Dev")
    @PreAuthorize("hasAnyRole('ADMIN, USER')")
    @GetMapping("/getProfileOrderList")
    public ResponseEntity<?> getProfileOrderList(@RequestParam(value = "page", defaultValue = "0") int page,
                                                 @RequestParam(value = "size", defaultValue = "5") int size){
        return ResponseEntity.ok(orderService.getProfileOrderList(page, size));
    }

}
