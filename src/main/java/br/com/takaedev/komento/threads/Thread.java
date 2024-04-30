package br.com.takaedev.komento.threads;

import br.com.takaedev.komento.base.Auditing;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Thread {
    @Id
    private String id;
    private String name;
    private String description;
    private Auditing auditing;
}
