package com.company.dto.auth;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
public class AuthDTO {
    @NotNull(message = "Email required")
    @Email(message = "Email required")
    private String email;


    @NotBlank(message = "Password required")
    @Size(min = 4,max = 15,message = "About me must be between 10 and 200 characters")
    private String password;
}