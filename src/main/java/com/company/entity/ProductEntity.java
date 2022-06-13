package com.company.entity;

import com.company.enums.product.ProductStatus;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "product")
public class ProductEntity {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    protected String id;
    @Column
    private String name;

    @Column(name = "attach_id")
    private String attach_id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attach_id",insertable = false,updatable = false)
    private AttachEntity attach;

    @Column(name = "category_id")
    private String category_id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id",insertable = false,updatable = false)
    private CategoryEntity category;


    @Column(name = "address_id")
    private String address_id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id",insertable = false,updatable = false)
    private MerchantEntity address;

    @Column
    private Double from_amount;
    @Column
    private Double to_amount;
    @Column
    private String durationDate;
    @Column
    private String description;

    @Column
    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    @Column
    private LocalDateTime publishedDate;
    @Column
    private LocalDateTime createDate;
}