package com.company.entity;

import com.company.enums.profile.ProfileRole;
import com.company.enums.profile.ProfileStatus;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "profile")
public class ProfileEntity {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    protected String id;
    @Column
    private String name;
    @Column
    private String surname;
    @Column
    private String email;
    @Column
    @Enumerated(EnumType.STRING)
    private ProfileRole role;
    @Column
    @Enumerated(EnumType.STRING)
    private ProfileStatus status;
    @Column
    private String phone;
    @Column
    private String password;
    @Column
    private String country;
    @Column
    private Boolean visible = true;
    @Column(name = "created_date")
    private LocalDateTime createdDate;

}
