package com.company.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
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
    private String duration;
    @Column
    private String back_date;

    @Column
    private LocalDateTime createDate;
}
