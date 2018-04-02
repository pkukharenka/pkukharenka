package ru.job4j.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Роли пользователей ресурса.
 *
 * @author Pyotr Kukharenka
 * @since 29.03.2018
 */

@Data
@NoArgsConstructor
@Entity
@Table(name = "ROLES")
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "ROLE_TYPE")
    private String roleType;

}
