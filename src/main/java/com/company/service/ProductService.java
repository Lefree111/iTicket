package com.company.service;

import com.company.dto.product.ProductDTO;
import com.company.entity.ProductEntity;
import com.company.enums.product.ProductStatus;
import com.company.exc.ItemNotFoundException;
import com.company.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductDTO create(ProductDTO dto){

        ProductEntity entity = new ProductEntity();
        entity.setName(dto.getName());
        entity.setAttach_id(dto.getAttach_id());
        entity.setCategory_id(dto.getCattegory_id());
        entity.setAddress(dto.getAddress());
        entity.setDescription(dto.getDescription());
        entity.setDurationDate(dto.getDurationDate());
        entity.setTo_amount(dto.getTo_amount());
        entity.setFrom_amount(dto.getFrom_amount());
        entity.setPublishedDate(LocalDateTime.now());
        entity.setCreateDate(LocalDateTime.now());

        entity.setStatus(ProductStatus.ACTIVE);
        productRepository.save(entity);
        dto.setId(entity.getId());
        return toDTO(entity);
    }

    public ProductDTO update(ProductDTO dto){
        Optional<ProductEntity> check = productRepository.findById(dto.getId());
        if (check.isPresent()){
            throw new ItemNotFoundException("Name not found");
        }

        ProductEntity entity = check.get();
        entity.setName(dto.getName());
        entity.setAttach_id(dto.getAttach_id());
        entity.setCategory_id(dto.getCattegory_id());
        entity.setAddress(dto.getAddress());
        entity.setDescription(dto.getDescription());
        entity.setDurationDate(dto.getDurationDate());
        entity.setTo_amount(dto.getTo_amount());
        entity.setFrom_amount(dto.getFrom_amount());
        entity.setPublishedDate(LocalDateTime.now());
        entity.setCreateDate(LocalDateTime.now());
        productRepository.save(entity);
        return toDTO(entity);
    }

    public ProductDTO getById(String id){
        Optional<ProductEntity> check = productRepository.findById(id);
        if (check.isPresent()){
            throw new ItemNotFoundException("Name not found");
        }
        return toDTO(check.get());
    }

    public String delete(String id){
        Optional<ProductEntity> check = productRepository.findById(id);
        if (check.isPresent()){
            throw new ItemNotFoundException("Name not found");
        }
        productRepository.delete(check.get());
        return "Success";
    }

    public ProductDTO toDTO(ProductEntity entity){
        ProductDTO dto = new ProductDTO();
        dto.setName(entity.getName());
        dto.setAttach_id(entity.getAttach_id());
        dto.setDescription(entity.getDescription());
        dto.setCattegory_id(entity.getCategory_id());
        dto.setDurationDate(entity.getDurationDate());
        dto.setStatus(entity.getStatus());
        dto.setTo_amount(entity.getTo_amount());
        dto.setFrom_amount(entity.getFrom_amount());
        dto.setCreateDate(entity.getCreateDate());
        return dto;
    }
}