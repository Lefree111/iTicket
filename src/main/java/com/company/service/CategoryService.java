package com.company.service;

import com.company.dto.category.CategoryDTO;
import com.company.entity.CategoryEntity;
import com.company.entity.ProfileEntity;
import com.company.enums.profile.ProfileRole;
import com.company.exc.AppBadRequestException;
import com.company.exc.CategoryAlredyExistsException;
import com.company.exc.ItemNotFoundException;
import com.company.repository.CategoryRepository;
import com.company.util.SpringSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryDTO create(CategoryDTO dto) {
        ProfileEntity profile = SpringSecurityUtil.getCurrentUser();

        if (!profile.getRole().equals(ProfileRole.ADMIN)){
            throw new AppBadRequestException("Not admin");
        }

        CategoryEntity category = categoryRepository.findByKey(dto.getKey());
        if (category != null) {
            throw new CategoryAlredyExistsException("Category Already Exists");
        }

        CategoryEntity entity = new CategoryEntity();
        entity.setName_en(dto.getName_en());
        entity.setName_ru(dto.getName_ru());
        entity.setName_uz(dto.getName_uz());
        entity.setKey(dto.getKey());
        entity.setProfileId(profile.getId());
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
        ProfileEntity profile = SpringSecurityUtil.getCurrentUser();
        CategoryEntity category = categoryRepository.findByKey(dto.getKey());
        if (category != null) {
            throw new CategoryAlredyExistsException("Category Already Exists");
        }

        if (!profile.getRole().equals(ProfileRole.ADMIN)){
            throw new AppBadRequestException("Not admin");
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
        ProfileEntity profile = SpringSecurityUtil.getCurrentUser();
        Optional<CategoryEntity> optional = categoryRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ItemNotFoundException("Id not found");
        }
        CategoryEntity entity = optional.get();
        entity.setProfileId(String.valueOf(profile));
        categoryRepository.delete(entity);
        return "Success";
    }


    public CategoryDTO toDTO(CategoryEntity entity) {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(entity.getId());
        dto.setKey(entity.getKey());
        dto.setName_en(entity.getName_en());
        dto.setName_ru(entity.getName_ru());
        dto.setName_uz(entity.getName_uz());
        dto.setCreateDate(entity.getCreateDate());
        dto.setUpdateDate(entity.getUpdateDate());
        return dto;
    }
}