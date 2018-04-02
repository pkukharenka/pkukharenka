package ru.job4j.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Справочник брэндов и моделей автомобилей.
 * Структура таблицы ввиде дерева.
 *
 * @author Pyotr Kukharenka
 * @since 28.03.2018
 */

@Data
@NoArgsConstructor
@Entity
@Table(name = "MODEL")
public class Model implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "MODEL_NAME")
    private String modelName;

    @ManyToOne
    @JoinColumn(
            name = "PARENT_ID",
            foreignKey = @ForeignKey(name = "FK_MODEL_PARENT_ID"))
    private Model parent;


    public Model(long id) {
        this.id = id;
    }
}
