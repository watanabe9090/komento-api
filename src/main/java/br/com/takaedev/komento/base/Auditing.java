package br.com.takaedev.komento.base;

import lombok.Data;

import java.time.Clock;
import java.time.LocalDateTime;

@Data
public class Auditing {
    private String createdBy;
    private String modifiedBy;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public static Auditing getNew(String creator) {
        Auditing auditing = new Auditing();
        auditing.setCreatedAt(LocalDateTime.now());
        auditing.setModifiedAt(LocalDateTime.now());
        auditing.setCreatedBy(creator);
        auditing.setModifiedBy(creator);
        return auditing;
    }
}
