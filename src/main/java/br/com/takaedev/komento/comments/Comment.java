package br.com.takaedev.komento.comments;

import br.com.takaedev.komento.base.BaseAuditing;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "comments")
@Data
public class Comment extends BaseAuditing {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String message;
}
