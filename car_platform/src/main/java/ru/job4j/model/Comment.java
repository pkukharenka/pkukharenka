package ru.job4j.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Комментарии к объявлениям о продаже авто.
 *
 * @author Pyotr Kukharenka
 * @since 29.03.2018
 */

@Data
@NoArgsConstructor
@Entity
@Table(name = "COMMENTS")
public class Comment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Lob
    @Column(name = "COMMENT_DESC")
    private String commentDesc;

    @Column(name = "COMMENT_CREATE")
    @CreationTimestamp
    private LocalDate commentCreate;

    @Generated(GenerationTime.ALWAYS)
    @Column(name = "COMMENT_MODIFIED", insertable = false, updatable = false)
    private LocalDate commentLastModified;

    @Column(name = "COMMENT_DEL")
    private boolean deleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CAR_ID", nullable = false)
    private Car car;

}
