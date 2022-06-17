package com.company.service;

import com.company.dto.profile.ProfileDTO;
import com.company.entity.ProfileEntity;
import com.company.enums.profile.ProfileRole;
import com.company.enums.profile.ProfileStatus;
import com.company.exc.EmailAlreadyExistsExeption;
import com.company.exc.ItemNotFoundException;
import com.company.exc.PhoneNumberAlreadyExistsExeption;
import com.company.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;

    @Autowired
    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public ProfileDTO create(ProfileDTO dto) {
        Optional<ProfileEntity> profileEmail = profileRepository.findByEmail(dto.getEmail());
        if (profileEmail.isPresent()) {
            throw new EmailAlreadyExistsExeption("Email Already Exists");
        }

        Optional<ProfileEntity> profilePhone = profileRepository.findByPhone(dto.getPhone());
        if (profilePhone.isPresent()) {
            throw new PhoneNumberAlreadyExistsExeption("Phone Number Already Exists");
        }

        ProfileEntity entity = new ProfileEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());
        entity.setPassword(dto.getPassword());
        entity.setRole(ProfileRole.USER);
        entity.setStatus(ProfileStatus.ACTIVE);
        entity.setCountry(dto.getCountry());
        entity.setCreatedDate(LocalDateTime.now());
        entity.setVisible(true);

        profileRepository.save(entity);

        dto.setId(entity.getId());
        dto.setRole(entity.getRole());
        dto.setStatus(entity.getStatus());
        dto.setVisible(entity.getVisible());
        dto.setCreated_date(entity.getCreatedDate());

        return dto;
    }

    public List<ProfileDTO> getList(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdDate"));

        Page<ProfileEntity> profileEntityList = profileRepository.findAll(pageable);

        List<ProfileDTO> profileDTOList = new LinkedList<>();

        profileEntityList.forEach(profileEntity -> {
            if (profileEntity.getVisible().equals(true)) {
                profileDTOList.add(toDTO(profileEntity));
            }
        });
        return profileDTOList;
    }

    public ProfileDTO update(String id, ProfileDTO dto) {
        ProfileEntity entity = profileRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("Profile not found"));

        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setEmail(entity.getEmail());
        entity.setPhone(dto.getPhone());
        entity.setPassword(dto.getPassword());
        entity.setRole(dto.getRole());
        entity.setCountry(dto.getCountry());
        entity.setStatus(dto.getStatus());
        entity.setStatus(dto.getStatus());

        profileRepository.save(entity);

        return dto;
    }

    public Boolean delete(String id) {
        profileRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("Profile not found"));
        return profileRepository.delete(false, id) > 1;
    }

    public ProfileDTO toDTO(ProfileEntity entity) {
        ProfileDTO dto = new ProfileDTO();
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setEmail(entity.getEmail());
        dto.setPhone(entity.getPhone());
        dto.setPassword(entity.getPassword());
        dto.setCountry(entity.getCountry());
        dto.setRole(entity.getRole());
        dto.setStatus(entity.getStatus());
        dto.setVisible(entity.getVisible());

        return dto;
    }

    public ProfileEntity get(String id) {
        return profileRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("Not Found!"));
    }


}
