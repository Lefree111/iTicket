package com.company.dto.merchant;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MerchantDTO {
    private String id;
    private String attachId;
    private String address;
    private String phone;
    private String profile_id;
    private LocalDateTime createDate;


}