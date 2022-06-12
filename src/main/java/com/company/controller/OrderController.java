package com.company.controller;

import com.company.dto.order.OrderDTO;
import com.company.enums.profile.ProfileRole;
import com.company.service.OrderService;
import com.company.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody OrderDTO dto, HttpServletRequest request) {
        dto.setProfileId(JWTUtil.getIdFromHeader(request));
        return ResponseEntity.ok(orderService.create(dto));
    }

    @GetMapping("/getList")
    public ResponseEntity<?> getList(@RequestParam(value = "page", defaultValue = "0") int size,
                                     @RequestParam(value = "page", defaultValue = "0") int page,
                                     HttpServletRequest request){
        JWTUtil.getIdFromHeader(request, ProfileRole.ADMIN);
        return ResponseEntity.ok(orderService.getList(page, size));
    }

    @GetMapping("/getProfileOrderList")
    public ResponseEntity<?> getProfileOrderList(@RequestParam(value = "page", defaultValue = "0") int size,
                                                 @RequestParam(value = "page", defaultValue = "0") int page,
                                                 HttpServletRequest request){
        String profileId = JWTUtil.getIdFromHeader(request, ProfileRole.USER);
        return ResponseEntity.ok(orderService.getProfileOrderList(profileId, page, size));
    }

}
