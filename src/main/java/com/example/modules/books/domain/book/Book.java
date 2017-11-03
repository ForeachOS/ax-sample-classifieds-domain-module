package com.example.modules.books.domain.book;

import com.example.modules.books.domain.author.Author;
import com.example.modules.books.domain.genre.Genre;
import com.foreach.across.modules.hibernate.id.AcrossSequenceGenerator;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Steven Gentens
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
@Table(name = "demo_book")
@Builder(toBuilder = true)
public class Book implements Persistable<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_cm_book_id")
    @GenericGenerator(
            name = "seq_cm_book_id",
            strategy = AcrossSequenceGenerator.STRATEGY,
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequenceName", value = "seq_cm_book_id"),
                    @org.hibernate.annotations.Parameter(name = "allocationSize", value = "1")
            }
    )
    private Long id;

    @Column
    @NotBlank
    @Length(max = 255)
    private String title;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @Column
    @Length(max = 2000)
    private String description;

    @Column
    private Status status;

    @Column(name = "publication_date")
    private Date publicationDate;

    @NotNull
    @Min( value = 0 )
    @Column
    private BigDecimal price;

    @Override
    public boolean isNew() {
        return id == null;
    }
}
