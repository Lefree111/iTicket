package com.company.dto.category;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
public class CategoryDTO {
    private String id;
    @NotNull(message = "name_en bo'sh qolmasin ")
    private String name_en;
    @NotNull(message = "name_ru bo'sh qolmasin ")
    private String name_ru;
    @NotNull(message = "name_uz bo'sh qolmasin ")
    private String name_uz;
    @NotNull(message = "key bo'sh qolmasin ")
    private String key;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

}