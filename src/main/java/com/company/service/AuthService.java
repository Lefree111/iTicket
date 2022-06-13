package com.company.service;

import com.company.dto.auth.AuthDTO;
import com.company.dto.profile.ProfileDTO;
import com.company.dto.profile.RegistrationDTO;
import com.company.entity.AttachEntity;
import com.company.entity.ProfileEntity;
import com.company.enums.profile.ProfileRole;
import com.company.enums.profile.ProfileStatus;
import com.company.exc.AppBadRequestException;
import com.company.exc.AppForbiddenException;
import com.company.exc.EmailAlreadyExistsExeption;
import com.company.exc.PasswordOrEmailWrongException;
import com.company.repository.ProfileRepository;
import com.company.util.JWTUtil;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Optional;

@Service
public class AuthService {

    private final ProfileRepository profileRepository;

    @Autowired
    public AuthService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public void registration(RegistrationDTO dto) {

        Optional<ProfileEntity> optional = profileRepository.findByEmail(dto.getEmail());
        if (optional.isPresent()) {
            throw new EmailAlreadyExistsExeption("email allaqachon yaratilgan");
        }

        ProfileEntity entity = new ProfileEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());

        entity.setRole(ProfileRole.USER);
        entity.setStatus(ProfileStatus.ACTIVE);
        profileRepository.save(entity);
    }

    public ProfileDTO login(AuthDTO dto) {
        Optional<ProfileEntity> optional = profileRepository.findByEmailAndPassword(dto.getEmail(), dto.getPassword());
        if (optional.isEmpty()) {
            throw new PasswordOrEmailWrongException("Password or email wrong!");
        }

        ProfileEntity entity = optional.get();
        if (!entity.getStatus().equals(ProfileStatus.ACTIVE)) {
            throw new AppForbiddenException("No Access bratishka.");
        }

        ProfileDTO profile = new ProfileDTO();
        profile.setEmail(entity.getEmail());
        profile.setPassword(entity.getPassword());
        profile.setJwt(JWTUtil.encode(entity.getId(), entity.getRole()));
        return profile;
    }

}
