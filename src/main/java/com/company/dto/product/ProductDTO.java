package com.company.dto.product;

import com.company.enums.product.ProductStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ProductDTO {
    private String id;
    private String name;
    private String attach_id;
    private String cattegory_id;
    private String address;
    private Double from_amount;
    private Double to_amount;
    private LocalDateTime durationDate;
    private String description;
    private ProductStatus status;
    private LocalDateTime publishedDate;
    private LocalDateTime createDate;
}
