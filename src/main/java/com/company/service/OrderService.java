package com.company.service;

import com.company.dto.order.OrderDTO;
import com.company.entity.OrderEntity;
import com.company.entity.ProfileEntity;
import com.company.repository.OrderRepository;
import com.company.util.SpringSecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderDTO create(OrderDTO dto) {
        ProfileEntity profile = SpringSecurityUtil.getCurrentUser();

        OrderEntity entity = new OrderEntity();
        entity.setProfileId(profile.getId());
        entity.setProduct_id(dto.getProduct_id());
        entity.setCreatedDate(LocalDateTime.now());

        orderRepository.save(entity);
        dto.setId(entity.getId());
        return toDTO(entity);
    }

    public List<OrderDTO> getList(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdDate"));

        Page<OrderEntity> orderEntitieList = orderRepository.findAll(pageable);

        List<OrderDTO> orderDTOList = new LinkedList<>();
        orderEntitieList.forEach(orderEntity -> {
            if (orderEntity.getVisible().equals(true)) {
                orderDTOList.add(toDTO(orderEntity));
            }
        });

        return orderDTOList;
    }

    public List<OrderDTO> getProfileOrderList(int page, int size) {
        ProfileEntity profile = SpringSecurityUtil.getCurrentUser();

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdDate"));

        Page<OrderEntity> orderEntitieList = orderRepository.findAllByProfileId(profile.getId(), pageable);

        List<OrderDTO> orderDTOList = new LinkedList<>();
        orderEntitieList.forEach(orderEntity -> {
            if (orderEntity.getVisible().equals(true)) {
                orderDTOList.add(toDTO(orderEntity));
            }
        });

        return orderDTOList;
    }

    public OrderDTO toDTO(OrderEntity entity) {
        OrderDTO dto = new OrderDTO();
        dto.setProfileId(entity.getProfileId());
        dto.setProduct_id(entity.getProduct_id());
        dto.setVisible(entity.getVisible());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }

}
