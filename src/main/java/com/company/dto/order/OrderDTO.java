package com.company.dto.order;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO {
    private String id;
    private String product_id;
    private String profileId;
    private Boolean visible;
    private LocalDateTime createdDate;
}
