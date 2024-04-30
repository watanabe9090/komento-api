package br.com.takaedev.komento.base;

import java.time.LocalDateTime;

public class Auditing {

    public static <T extends BaseAuditing> void neow(T auditing, String creator) {
        auditing.setCreatedAt(LocalDateTime.now());
        auditing.setModifiedAt(LocalDateTime.now());
        auditing.setCreatedBy(creator);
        auditing.setModifiedBy(creator);
    }
}
