package br.com.takaedev.komento.base;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BaseAuditing {
    private String createdBy;
    private String modifiedBy;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
