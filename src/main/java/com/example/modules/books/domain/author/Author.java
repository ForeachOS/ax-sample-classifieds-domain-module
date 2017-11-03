package com.example.modules.books.domain.author;

import com.foreach.across.modules.hibernate.id.AcrossSequenceGenerator;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;

/**
 * @author Steven Gentens
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
@Table(name = "demo_author")
@Builder(toBuilder = true)
public class Author implements Persistable<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_cm_author_id")
    @GenericGenerator(
            name = "seq_cm_author_id",
            strategy = AcrossSequenceGenerator.STRATEGY,
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequenceName", value = "seq_cm_author_id"),
                    @org.hibernate.annotations.Parameter(name = "allocationSize", value = "1")
            }
    )
    private Long id;

    @Column
    @NotBlank
    @Length(max = 255)
    private String name;

    @Column(name="gravatar_url")
    @Length(max = 255)
    private String gravatarUrl;

    @Column
    @NotBlank
    @Length(max = 255)
    private String email;

    @Override
    public boolean isNew() {
        return id == null;
    }
}
