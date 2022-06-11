package com.company.dto.profile;

import com.company.enums.profile.ProfileRole;
import com.company.enums.profile.ProfileStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfileDTO {
    private String id;
    private String name;
    private String surname;
    private String email;
    private ProfileRole role;
    private ProfileStatus status;
    private String phone;
    private String password;
    private String country;
    private Boolean visible;
    private String jwt;
    private LocalDateTime created_date;
}
