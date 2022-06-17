package com.company.service;

import com.company.dto.merchant.MerchantDTO;
import com.company.entity.MerchantEntity;
import com.company.entity.ProfileEntity;
import com.company.exc.ItemNotFoundException;
import com.company.repository.MerchantRepository;
import com.company.util.SpringSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class MerchantService {

    private final MerchantRepository merchantRepository;

    @Autowired
    public MerchantService(MerchantRepository merchantRepository) {
        this.merchantRepository = merchantRepository;
    }

    public MerchantDTO create(MerchantDTO dto) {
        ProfileEntity profile = SpringSecurityUtil.getCurrentUser();
        Optional<MerchantEntity> optional = merchantRepository.findByAddress(dto.getAddress());
        if (optional.isPresent()){
            throw new ItemNotFoundException("address yaratilgan ");
        }
        MerchantEntity entity = new MerchantEntity();
        entity.setAttach_id(dto.getAttachId());
        entity.setAddress(dto.getAddress());
        entity.setProfile_id(profile.getId());
        entity.setPhone(dto.getPhone());
        entity.setCreateDate(LocalDateTime.now());
        merchantRepository.save(entity);
        return toDTO(entity);
    }

    public MerchantDTO update(String id, MerchantDTO dto) {
        ProfileEntity profile = SpringSecurityUtil.getCurrentUser();
        MerchantEntity entity = merchantRepository.findById(id).orElseThrow(() -> {
            throw new ItemNotFoundException("Id not found");
        });

        entity.setPhone(dto.getPhone());
        entity.setAttach_id(dto.getAttachId());
        entity.setProfile_id(profile.getId());
        merchantRepository.save(entity);
        return toDTO(entity);
    }

    public MerchantDTO getById(String id) {
        MerchantEntity entity = merchantRepository.findById(id).orElseThrow(() -> {
            throw new ItemNotFoundException("Id not found");
        });
        return toDTO(entity);
    }

    public MerchantDTO getByAddress(String address) {
        MerchantEntity entity = merchantRepository.findByAddress(address).orElseThrow(() -> {
            throw new ItemNotFoundException("Id not found");
        });
        return toDTO(entity);
    }

    public String delete(String id) {
        MerchantEntity entity = merchantRepository.findById(id).orElseThrow(() -> {
            throw new ItemNotFoundException("Id not found");
        });
        merchantRepository.delete(entity);
        return "Success";
    }

    public MerchantDTO toDTO(MerchantEntity entity) {
        MerchantDTO dto = new MerchantDTO();
        dto.setId(entity.getId());
        dto.setAttachId(entity.getAttach_id());
        dto.setPhone(entity.getPhone());
        dto.setAddress(entity.getAddress());
        dto.setCreateDate(entity.getCreateDate());
        return dto;
    }
}