package com.company.service;

import com.company.dto.order.OrderDTO;
import com.company.entity.OrderEntity;
import com.company.repository.OrderRepository;
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

    private final ProductService productService;

    public OrderDTO create(OrderDTO dto) {
//        productService.get(dto.getPrdouctId());

        OrderEntity entity = new OrderEntity();
        entity.setProfileId(dto.getProfileId());
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

    public List<OrderDTO> getProfileOrderList(String profileId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdDate"));

        Page<OrderEntity> orderEntitieList = orderRepository.findAllByProfileId(profileId, pageable);

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
