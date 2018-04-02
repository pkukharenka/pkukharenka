package ru.job4j.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Объявление о продаже машины.
 *
 * @author Pyotr Kukharenka
 * @since 28.03.2018
 */

@Data
@NoArgsConstructor
@Entity
@Table(name = "CAR_OFFER")
public class Car implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "PRICE")
    private BigDecimal price;

    @Column(name = "CAR_CREATED", updatable = false)
    @CreationTimestamp
    private LocalDate carCreate;

    @Generated(GenerationTime.ALWAYS)
    @Column(name = "CAR_MODIFIED")
    private LocalDate carLastModified;

    @Column(name = "CAR_DESC")
    private String description;

    @Column(name = "MILEAGE")
    private int mileage;

    @Column(name = "CREATE_YEAR")
    private int createYear;

    @Column(name = "SELL")
    @Basic(optional = false)
    private boolean sell;

    @Column(name = "CAR_DEL")
    @Basic(optional = false)
    private boolean deleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User holder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MODEL_ID", nullable = false)
    private Model model;

    @OneToMany(mappedBy = "car")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "car")
    private List<CarImage> images = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "OPTION_CAR",
            joinColumns = @JoinColumn(name = "CAR_ID"),
            inverseJoinColumns = @JoinColumn(name = "CAR_OPTION_ID")
    )
    private List<Option> options = new ArrayList<>();
}
