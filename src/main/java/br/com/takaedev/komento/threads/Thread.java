package br.com.takaedev.komento.threads;

import br.com.takaedev.komento.base.BaseAuditing;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "threads")
@Data
public class Thread extends BaseAuditing {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
}
