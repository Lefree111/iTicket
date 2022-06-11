package com.company.service;

import com.company.dto.category.CategoryDTO;
import com.company.entity.CategoryEntity;
import com.company.exc.CategoryAlredyExistsException;
import com.company.exc.ItemNotFoundException;
import com.company.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryDTO create(CategoryDTO dto) {
        CategoryEntity category = categoryRepository.findByKey(dto.getKey());
        if (category != null) {
            throw new CategoryAlredyExistsException("Category Already Exists");
        }

        CategoryEntity entity = new CategoryEntity();
        entity.setName_en(dto.getName_en());
        entity.setName_ru(dto.getName_ru());
        entity.setName_uz(dto.getName_uz());
        entity.setKey(dto.getKey());
        entity.setCreateDate(LocalDateTime.now());
        categoryRepository.save(entity);
        dto.setId(entity.getId());
        return toDTO(entity);
    }

    public CategoryDTO getById(String id) {
        CategoryEntity entity = categoryRepository.findById(id).orElseThrow(() -> {
            throw new ItemNotFoundException("Id not found");
        });
        return toDTO(entity);
    }

    public CategoryDTO update(String id, CategoryDTO dto) {
        CategoryEntity category = categoryRepository.findByKey(dto.getKey());
        if (category != null) {
            throw new CategoryAlredyExistsException("Category Already Exists");
        }

        CategoryEntity entity = categoryRepository.findById(id).orElseThrow(() -> {
            throw new ItemNotFoundException("Id not found");
        });
        entity.setName_en(dto.getName_en());
        entity.setName_ru(dto.getName_ru());
        entity.setName_uz(dto.getName_uz());
        entity.setKey(dto.getKey());
        entity.setUpdateDate(LocalDateTime.now());
        categoryRepository.save(entity);
        return toDTO(entity);
    }

    public String delete(String id) {
        Optional<CategoryEntity> optional = categoryRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ItemNotFoundException("Id not found");
        }
        CategoryEntity entity = optional.get();
        categoryRepository.delete(entity);
        return "Success";
    }


    public CategoryDTO toDTO(CategoryEntity entity) {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(entity.getId());
        dto.setKey(entity.getKey());
        dto.setCreateDate(entity.getCreateDate());
        dto.setUpdateDate(entity.getUpdateDate());
        return dto;
    }
}