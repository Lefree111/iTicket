package com.company.dto.attach;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AttachDTO {
    private String id;
    private String originName;
    private Long size;
    private String extension;
    private String path;
    private String url;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    public AttachDTO() {
    }

    public AttachDTO(String url) {
        this.url = url;
    }
}