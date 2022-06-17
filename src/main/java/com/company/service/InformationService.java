package com.company.service;


import com.company.dto.product.InformationDTO;
import com.company.entity.InformationEntity;
import com.company.exc.ItemNotFoundException;
import com.company.repository.InformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class InformationService {

    private final InformationRepository informationRepository;

    @Autowired
    public InformationService(InformationRepository informationRepository) {
        this.informationRepository = informationRepository;
    }

    public InformationDTO create(InformationDTO dto) {

        InformationEntity entity = new InformationEntity();
        entity.setAge_limit(dto.getAge_limit());
        entity.setLanguage(dto.getLanguage());
        entity.setBack_date(dto.getBack_date_ticket());
        entity.setDuration(dto.getDuration());
        entity.setCreateDate(LocalDateTime.now());
        informationRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    public InformationDTO update(String id, InformationDTO dto) {

        InformationEntity entity = informationRepository.findById(id).orElseThrow(() -> {
            throw new ItemNotFoundException("Id not found");
        });
        entity.setLanguage(dto.getLanguage());
        entity.setAge_limit(dto.getAge_limit());
        entity.setBack_date(dto.getBack_date_ticket());
        entity.setDuration(dto.getDuration());
        informationRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }


    public InformationDTO getById(String id) {
        InformationEntity entity = informationRepository.findById(id).orElseThrow(() -> {
            throw new ItemNotFoundException("Id not found");
        });
        return toDTO(entity);
    }

    public String delete(String id) {
        Optional<InformationEntity> optional = informationRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ItemNotFoundException("Id not found");
        }
        InformationEntity entity = optional.get();
        informationRepository.delete(entity);
        return "Success";
    }

    public InformationEntity get(String id) {
        return informationRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("Id not found"));
    }

    public InformationDTO toDTO(InformationEntity entity) {
        InformationDTO dto = new InformationDTO();
        dto.setId(entity.getId());
        dto.setAge_limit(entity.getAge_limit());
        dto.setLanguage(entity.getLanguage());
        dto.setBack_date_ticket(entity.getBack_date());
        dto.setDuration(entity.getDuration());
        dto.setCreateDate(entity.getCreateDate());
        return dto;
    }
}