package com.company.service;

import com.company.dto.product.ProductDTO;
import com.company.dto.profile.ProfileDTO;
import com.company.entity.ProductEntity;
import com.company.entity.ProfileEntity;
import com.company.enums.product.ProductStatus;
import com.company.exc.AppBadRequestException;
import com.company.exc.ItemNotFoundException;
import com.company.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
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
        entity.setMerchant_id(dto.getMerchant());
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

    public List<ProductDTO> getList(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createDate"));

        Page<ProductEntity> profileEntityList = productRepository.findAll(pageable);

        List<ProductDTO> profileDTOList = new LinkedList<>();

        profileEntityList.forEach(product -> {
            if (product.getStatus().equals(ProductStatus.ACTIVE)){
                profileDTOList.add(toDTO(product));
            }
        });
        return profileDTOList;
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
        entity.setMerchant_id(dto.getMerchant());
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
        ProductEntity entity = check.get();
        if (!entity.getStatus().equals(ProductStatus.ACTIVE)) {
            throw new ItemNotFoundException("Status active emas");
        }
        return toDTO(check.get());
    }


    public Boolean changeStatus(String id, ProductStatus status) {
        ProductEntity entity = get(id);
        try {
            if (entity.getStatus().equals(status)) {
                return false;
            }
            entity.setStatus(status);
            return productRepository.updateStatus(status, id) > 0;

        } catch (RuntimeException e) {
            throw new AppBadRequestException("Status not valid!");
        }
    }



    public ProductEntity get(String id) {
        return productRepository.findById(id).orElseThrow(() -> {
            throw new ItemNotFoundException("Article Not found");
        });
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
        dto.setMerchant(entity.getMerchant_id());
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