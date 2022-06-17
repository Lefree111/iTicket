package com.company.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "merchant")
public class MerchantEntity {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;
    @Column(name = "attach_id")
    private String attach_id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attach_id",insertable = false,updatable = false)
    private AttachEntity attach;

    @Column
    private String address;
    @Column
    private String phone;
    @Column
    private String profile_id;
    @Column
    private LocalDateTime createDate;
}