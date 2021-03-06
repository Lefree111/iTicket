package com.company.dto.profile;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class RegistrationDTO {
    private String name;
    private String surname;
    @NotNull(message = "email null ku mazgi")
    private String email;
    @NotNull(message = "phone null ku mazgi")
    private String phone;
    @NotNull(message = "password qani Mazgi")
    private String password;
    private String country;

}
