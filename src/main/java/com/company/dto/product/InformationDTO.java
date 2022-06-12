package com.company.dto.product;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Getter
@Setter
public class InformationDTO {
    private String id;
    private Integer age_limit;
    private String language;
    private String duration;
    private String back_date_ticket;
    private String product_id;
    private LocalDateTime createDate;

}
