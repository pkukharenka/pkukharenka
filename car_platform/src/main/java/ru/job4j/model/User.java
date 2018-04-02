package ru.job4j.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * Пользователи ресурса.
 *
 * @author Pyotr Kukharenka
 * @since 29.03.2018
 */

@Data
@NoArgsConstructor
@Entity
@Table(name = "USERS")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "USER_NAME")
    private String name;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROLE_ID", nullable = false)
    private Role role;

    @Column(name = "USER_CREATED", updatable = false)
    @CreationTimestamp
    private LocalDate createdOn;

    @Generated(GenerationTime.ALWAYS)
    @Column(name = "USER_MODIFIED", insertable = false, updatable = false)
    private LocalDate lastModified;

    @OneToMany(mappedBy = "holder", fetch = FetchType.LAZY)
    private List<Car> cars;

    public User(long id) {
        this.id = id;
    }
}
