package com.company.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
@Getter
@Setter
@Entity
@Table(name = "information")
public class InformationEntity {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    protected String id;
    @Column
    private Integer age_limit;
    @Column
    private String language;
    @Column
    private LocalDateTime duration;
    @Column
    private LocalDateTime back_date;

    @Column(name = "product_id")
    private String product_id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id",insertable = false,updatable = false)
    private ProductEntity  product;

    @Column
    private LocalDateTime createDate;
}
