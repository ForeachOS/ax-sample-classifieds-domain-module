package com.example.modules.classifieds.domain.classified;

import com.example.modules.classifieds.domain.category.Category;
import com.example.modules.classifieds.domain.seller.Seller;
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
@Table(name="demo_classified")
@Builder(toBuilder = true)
public class Classified implements Persistable<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_cm_classified_id")
    @GenericGenerator(
            name = "seq_cm_classified_id",
            strategy = AcrossSequenceGenerator.STRATEGY,
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequenceName", value = "seq_cm_classified_id"),
                    @org.hibernate.annotations.Parameter(name = "allocationSize", value = "1")
            }
    )
    private Long id;

    @Column
    @NotBlank
    @Length(max = 255)
    private String name;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Seller seller;

    @Column
    @Length(max = 2000)
    private String description;

    @Column
    private Status status;

    @Column(name="available_after")
    private Date availableAfter;

    @NotNull
    @Min( value = 0 )
    @Column
    private BigDecimal price;

    @Override
    public boolean isNew() {
        return id == null;
    }
}
