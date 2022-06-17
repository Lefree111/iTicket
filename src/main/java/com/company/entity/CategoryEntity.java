package com.company.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "category")
public class CategoryEntity {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    protected String id;
    @Column
    private String name_en;
    @Column
    private String name_ru;
    @Column
    private String name_uz;
    @Column
    private String key;
    @Column
    private String profileId;
    @Column
    private LocalDateTime createDate;
    @Column
    private LocalDateTime updateDate;
}