package ru.job4j.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.job4j.util.Constants;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Фотографии продающихся автомобилей
 *
 * @author Pyotr Kukharenka
 * @since 29.03.2018
 */

@Data
@NoArgsConstructor
@Entity
@Table(name = "IMAGES")
public class CarImage implements Serializable {

    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    private long id;

    @Column(name = "IMAGE_NAME")
    private String imageName;

    @Column(name = "IMAGE")
    private String imagePath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CAR_ID", nullable = false)
    private Car car;

}
