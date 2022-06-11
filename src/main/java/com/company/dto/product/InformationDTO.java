package com.company.dto.product;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class InformationDTO {
    private String id;
    private Integer age_limit;
    private String language;
    private LocalDateTime duration;
    private LocalDateTime back_date_ticket;
    private String product_id;
    private LocalDateTime createDate;

}
