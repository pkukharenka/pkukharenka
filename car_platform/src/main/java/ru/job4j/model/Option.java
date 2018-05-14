package ru.job4j.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.job4j.util.Constants;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Всевозможные опции автомобилей для более детального
 * составления объявлений о продаже, а также поиска
 * автомобилей с нужными характеристиками. Структура таблицы
 * дерево.
 *
 * @author Pyotr Kukharenka
 * @since 31.03.2018
 */

@Data
@NoArgsConstructor
@Entity
@Table(name = "OPTION")
public class Option implements Serializable {

    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    private long id;

    @Column(name = "OPTION_NAME")
    private String optionName;

    @ManyToOne
    @JoinColumn(
            name = "PARENT_ID",
            foreignKey = @ForeignKey(name = "FK_OPTION_PARENT_ID")
    )
    private Option parentOption;

}
