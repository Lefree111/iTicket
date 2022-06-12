package com.company.dto.profile;

import com.company.enums.profile.ProfileRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfileJwtDto {
    @NotNull(message = "Id null ku mazgi")
    private String id;
    @NotNull(message = "role null ku mazgi")
    private ProfileRole role;
}
